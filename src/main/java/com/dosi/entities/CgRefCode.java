package com.dosi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@Entity
@Table(name = "CG_REF_CODES")
public class CgRefCode {
    @Id
    @Column(name = "ID_CGRC", nullable = false)
    private Long id;

    @Column(name = "RV_DOMAIN", nullable = false, length = 100)
    private String rvDomain;

    @Column(name = "RV_LOW_VALUE", nullable = false, length = 240)
    private String rvLowValue;

    @Column(name = "RV_HIGH_VALUE", length = 240)
    private String rvHighValue;

    @Column(name = "RV_ABBREVIATION", length = 240)
    private String rvAbbreviation;

    @Column(name = "RV_MEANING", length = 240)
    private String rvMeaning;

}