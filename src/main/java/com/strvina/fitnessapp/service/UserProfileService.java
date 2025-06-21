// UserProfileService.java
package com.strvina.fitnessapp.service;

import com.strvina.fitnessapp.model.UserProfile;
import com.strvina.fitnessapp.repository.UserProfileRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepo  userProfileRepository;

    public UserProfileService(UserProfileRepo repository) {
        this.userProfileRepository = repository;
    }

    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    public UserProfile findByUserId(Long userId) {
        return userProfileRepository.findByUserId(userId);
    }

    public UserProfile save(UserProfile profile) {
        if (profile.getDietRestrictions() != null) {
            profile.getDietRestrictions().forEach(r -> r.setUserProfile(profile));
        }
        return userProfileRepository.save(profile);
    }
}
