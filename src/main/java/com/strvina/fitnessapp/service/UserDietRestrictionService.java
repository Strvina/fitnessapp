// UserDietRestrictionService.java
package com.strvina.fitnessapp.service;

import com.strvina.fitnessapp.model.UserDietRestriction;
import com.strvina.fitnessapp.repository.UserDietRestrictionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDietRestrictionService {

    private final UserDietRestrictionRepo userDietRepository;

    public UserDietRestrictionService(UserDietRestrictionRepo repository) {
        this.userDietRepository = repository;
    }

    public List<UserDietRestriction> findByUserProfileId(Long id) {
        return userDietRepository.findByUserProfileId(id);
    }

    public UserDietRestriction save(UserDietRestriction restriction) {
        return userDietRepository.save(restriction);
    }
}
