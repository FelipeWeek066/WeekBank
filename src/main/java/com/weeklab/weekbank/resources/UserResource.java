package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.DTOs.DoDepositDTO;
import com.weeklab.weekbank.entities.DTOs.UserDTO;
import com.weeklab.weekbank.entities.DTOs.mappers.UserMapper;
import com.weeklab.weekbank.entities.Deposit;
import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    private UserService service;
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(UserMapper.INSTANCE.usersToUserDtos(service.findAll()));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body(UserMapper.INSTANCE.userToUserDTO(service.findById(id)));
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody User user){
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DoDepositDTO doDepositDTO, Authentication authentication){
        System.out.println(authentication.getName());
        System.out.println(service.findByName(authentication.getName()).getName());

        Deposit deposit = DoTransactionDTOToTransaction(doDepositDTO);
        deposit.setPayer(service.findByName(authentication.getName()));
        service.deposit(deposit);
        return ResponseEntity.noContent().build();
    }

    private Deposit DoTransactionDTOToTransaction(DoDepositDTO depositDto){
        Deposit deposit = new Deposit();
        deposit.setAmount(depositDto.getAmount());
        deposit.setDate(LocalDateTime.now());
        User payee = service.findByName(depositDto.getPayee());
        System.out.println(service.findByName(depositDto.getPayee()));
        deposit.setPayee(payee);
        return deposit;
    }
}
