package com.strvina.fitnessapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_diet_restriction")
public class UserDietRestriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @Column(name = "restriction_type", nullable = false)
    private String restrictionType;

    @Column(name = "item_name", nullable = false)
    private String itemName;


}


