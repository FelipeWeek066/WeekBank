package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.DTOs.DepositDTO;
import com.weeklab.weekbank.entities.DTOs.mappers.DepositMapper;
import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.services.DepositService;
import com.weeklab.weekbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposits")
public class DepositResource {

    @Autowired
    private DepositService service;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<DepositDTO>> findAll(){

        return ResponseEntity.ok().body(DepositMapper.INSTANCE.depositsToDTOs((service.findAll())));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<List<DepositDTO>> findByName(@PathVariable String name){
        User user = userService.findByName(name);
        return ResponseEntity.ok().body(DepositMapper.INSTANCE.depositsToDTOs((service.findByUser(user))));
    }




  /*  @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
