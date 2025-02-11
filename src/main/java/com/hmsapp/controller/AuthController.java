package com.hmsapp.controller;

import com.hmsapp.entity.User;
import com.hmsapp.payload.JwtToken;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.repository.UserRepository;
import com.hmsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private int log_rounds;
    private UserService userService;



    public AuthController(UserRepository userRepository,UserService userService) {

        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody User user) {
      Optional<User>opUsername= userRepository.findByUsername(user.getUsername());
      if (opUsername.isPresent()) {
          return new ResponseEntity("Username already exist", HttpStatus.INTERNAL_SERVER_ERROR);
      }
        Optional<User>opEmail= userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity("email already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User>opMobile= userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            return new ResponseEntity("mobile already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole("ROLE_USER");
        User savedUser =userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/property/sign-up")
    public ResponseEntity<?> createPropertyOwnerAccount(@RequestBody User user) {
        Optional<User>opUsername= userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity("Username already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User>opEmail= userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity("email already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User>opMobile= userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            return new ResponseEntity("mobile already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole("ROLE_OWNER");
        User savedUser =userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/blog/sign-up")
    public ResponseEntity<?> createBlogManagerAccount(@RequestBody User user) {
        Optional<User>opUsername= userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity("Username already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User>opEmail= userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity("email already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User>opMobile= userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            return new ResponseEntity("mobile already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole("ROLE_BLOGMANAGER");
        User savedUser =userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
@PostMapping("/login")
public ResponseEntity<?> login( @RequestBody LoginDto loginDto){
       String token= userService.verifyLogin(loginDto);
       JwtToken jwtToken=new JwtToken();
       jwtToken.setToken(token);
       jwtToken.setType("jwt");
       if(token!=null){
           return new ResponseEntity<>(jwtToken,HttpStatus.OK);
       }

    return new ResponseEntity<>("invalid",HttpStatus.INTERNAL_SERVER_ERROR);
}
}
