package com.dogboard.dogboard.Users;

import com.dogboard.dogboard.Models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getProfiles() {
        return userService.getProfiles();
    }

    @PostMapping
    public ResponseEntity<UserEntity> createProfile(
            @RequestBody UserEntity profile
    ) {
        UserEntity createdProfile = userService.createProfile(profile);
        return ResponseEntity.ok(createdProfile);
    }
}

