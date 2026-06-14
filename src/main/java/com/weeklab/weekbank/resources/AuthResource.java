package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.DTOs.AuthDTO;
import com.weeklab.weekbank.entities.DTOs.LoginResponseDTO;
import com.weeklab.weekbank.entities.DTOs.RegisterDTO;
import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.repositories.UserRepository;
import com.weeklab.weekbank.resources.handlers.ExceptionHandlers;
import com.weeklab.weekbank.services.TokenService;
import com.weeklab.weekbank.services.exceptions.NameAlreadyChoosenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthDTO login){
        var userPassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
        var auth = this.authenticationManager.authenticate(userPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO register){
        System.out.println(repository.findByName(register.getLogin()));
        if(repository.findByName(register.getLogin()).isPresent()){
            throw new NameAlreadyChoosenException("this name was already taken.");
        }else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(register.getPassword());
            User user = new User(register.getLogin(), encryptedPassword, register.getRole());
            repository.save((user));
            return ResponseEntity.ok().build();
        }
    }
}
