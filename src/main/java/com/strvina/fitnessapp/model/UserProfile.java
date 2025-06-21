package com.strvina.fitnessapp.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfile {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotBlank(message = "Cilj je obavezan")
    private String goal;

    @NotBlank(message = "Tip treninga je obavezan")
    @Column(name = "training_type")
    private String trainingType;

    @NotNull(message = "Težina je obavezna")
    @Min(value = 20, message = "Težina mora biti najmanje 20 kg")
    @Max(value = 500, message = "Težina ne može biti veća od 500 kg")
    private Integer weight;

    @NotNull(message = "Visina je obavezna")
    @Min(value = 50, message = "Visina mora biti najmanje 50 cm")
    @Max(value = 300, message = "Visina ne može biti veća od 300 cm")
    private Integer height;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "deleted_at", updatable = false, insertable = false)
    private LocalDateTime deletedAt;
    
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDietRestriction> dietRestrictions;
}
