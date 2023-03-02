package com.dosi.services.auth;

import com.dosi.auth.AuthenticationRequest;
import com.dosi.auth.AuthenticationResponse;
import com.dosi.auth.RegisterRequest;
import com.dosi.config.JwtService;
import com.dosi.entities.Role;
import com.dosi.entities.User;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.EtudiantRepository;
import com.dosi.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final EnseignantRepository enseignantRepository;
    private final EtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println(request);
        if( !StringUtils.isBlank(request.getEmail()) && userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new EntityExistsException("Email " +  request.getEmail() + " existe déjà!");
        }

        boolean isEnseignant = StringUtils.isBlank(request.getNoEtudiant()) && (request.getNoEnseignant() != null);
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()) )
                .noEnseignant(request.getNoEnseignant())
                .noEtudiant(request.getNoEtudiant())
                .role( !isEnseignant ? Role.ETU : Role.ENS)
                .build();
        if( isEnseignant )
        {
            enseignantRepository.findByEmailUbo(request.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "l'email de l'Enseignant " + request.getEmail() + " n'a pas été trouvée."));
            enseignantRepository.findById(request.getNoEnseignant()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "l'Enseignant avec le Numéro " + request.getNoEnseignant() + " n'a pas été trouvée."));
        }
        else
        {
            etudiantRepository.findByEmailUbo(request.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "l'email de l'Etudiant " + request.getEmail() + " n'a pas été trouvée."));
            etudiantRepository.findById(request.getNoEtudiant()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "l'Etudiant avec l'ID " + request.getNoEtudiant() + " n'a pas été trouvée."));
        }
        try{
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e);
        }
                var jwtToken = jwtService.generateToken(user);
        System.out.println();
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        System.out.println(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found!"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
