package com.dogboard.dogboard.Controllers;

import com.dogboard.dogboard.DTO.AuthResponseDTO;
import com.dogboard.dogboard.DTO.UserDto;
import com.dogboard.dogboard.Models.Role;
import com.dogboard.dogboard.Models.UserEntity;
import com.dogboard.dogboard.Repository.RoleRepository;
import com.dogboard.dogboard.Repository.UserRepository;
import com.dogboard.dogboard.security.JWTGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private JWTGenerator jwtGenerator;


    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtGenerator.generateToken(authentication);
        UserEntity foundUser = userRepository.findByUsername(userDto.getUsername()).orElseThrow();

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(jwt);
        authResponseDTO.setUserId(foundUser.getUserId());

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UserDto userDto) {
        if(userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role roles = roleRepository.findByName("USER").orElse(null);
        userEntity.setRoles(Collections.singletonList(roles));

        userRepository.save(userEntity);

        //        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtGenerator.generateToken(authentication);
//
//        AuthResponseDTO authResponseDTO = new AuthResponseDTO(jwt);
//        authResponseDTO.setUserId(savedUser.getUserId());

        return login(userDto);
    }
}
