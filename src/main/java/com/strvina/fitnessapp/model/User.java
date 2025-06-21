package com.strvina.fitnessapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.Instant;

import jakarta.persistence.Column;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email je obavezan")
    @Email(message = "Email nije validan")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Lozinka je obavezna")
    @Size(min = 8, message = "Lozinka mora imati najmanje 8 karaktera")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
        message = "Lozinka mora sadržati veliko slovo, malo slovo, broj i specijalni karakter"
    )
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Ime je obavezno")
    private String name;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    private Boolean isActive = true; 

    private String role = "USER";

    @Column(name = "deleted_at", updatable = false, insertable = false)
    private Instant deletetAt;

}
