package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.DTOs.AuthDTO;
import com.weeklab.weekbank.entities.DTOs.CodeValidateDTO;
import com.weeklab.weekbank.entities.DTOs.LoginResponseDTO;
import com.weeklab.weekbank.entities.DTOs.RegisterDTO;
import com.weeklab.weekbank.entities.Deposit;
import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.entities.UserCode;
import com.weeklab.weekbank.repositories.UserCodeRepository;
import com.weeklab.weekbank.repositories.UserRepository;
import com.weeklab.weekbank.services.TokenService;
import com.weeklab.weekbank.services.UserService;
import com.weeklab.weekbank.services.exceptions.ContentNotFoundException;
import com.weeklab.weekbank.services.exceptions.NameAlreadyChoosenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCodeRepository codeRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthDTO login){
        var userPassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
        var auth = this.authenticationManager.authenticate(userPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token, login.getLogin()));
    }


    @PostMapping("/checkCode")
    public ResponseEntity<User> validate(@RequestBody CodeValidateDTO code){
       //to implement
        UserCode userCode = codeRepository.findByCode(code.getCode()).orElseThrow(() -> new ContentNotFoundException("invalid code: " + code.getCode()));
        User user = new User(userCode.getName(), null, userCode.getRole());
        user.setDeleted(false);
        user.setEntryDate(LocalDateTime.now());
        userService.insert((user));
        codeRepository.deleteById(userCode.getId());
        URI uri = ServletUriComponentsBuilder.fromPath("/users").path("/{id}").buildAndExpand(userCode.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //change that
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO register){

        if(userRepository.existsByName(register.getLogin())){
            throw new NameAlreadyChoosenException("this name was already taken.");
        }else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(register.getPassword());
            User user = new User(register.getLogin(), encryptedPassword, register.getRole());
            userService.insert((user));
            return ResponseEntity.ok().build();
        }
    }


}
