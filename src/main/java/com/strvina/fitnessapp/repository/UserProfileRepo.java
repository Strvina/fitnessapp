package com.strvina.fitnessapp.repository;

import com.strvina.fitnessapp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
    UserProfile findByUserId(Long userId);
}
