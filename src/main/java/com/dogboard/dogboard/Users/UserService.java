package com.dogboard.dogboard.Users;

import com.dogboard.dogboard.Models.UserEntity;
import com.dogboard.dogboard.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getProfiles() {
        return userRepository.findAll();
    }

    public UserEntity createProfile(UserEntity profile) {
        return userRepository.save(profile);
    }
}
