package com.dosi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="AUTHENTIFICATION")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name="aut_seq", sequenceName = "aut_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="aut_seq")
    @Column(name = "ID_CONNECTION", nullable = false)
    private Long id;
    @Column(name = "ROLE", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "LOGIN_CONNECTION", nullable = false, length = 64)
    private String email;

    @Column(name = "PSEUDO_CONNECTION", length = 240)
    private String pseudoConnection;

    @Column(name = "MOT_PASSE", length = 32)
    private String password;
//    private String password;


    @Column(name = "NO_ENSEIGNANT")
    private Long noEnseignant;

    @Column(name = "NO_ETUDIANT", length = 50)
    private String noEtudiant;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
