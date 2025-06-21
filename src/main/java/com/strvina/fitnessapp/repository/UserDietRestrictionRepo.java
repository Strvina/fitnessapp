package com.strvina.fitnessapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.strvina.fitnessapp.model.UserDietRestriction;

import java.util.List;

public interface UserDietRestrictionRepo extends JpaRepository<UserDietRestriction, Long> {
    List<UserDietRestriction> findByUserProfileId(Long userProfileId);
}


