package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.DTOs.DepositDTO;
import com.weeklab.weekbank.entities.DTOs.DoDepositDTO;
import com.weeklab.weekbank.entities.DTOs.UserDTO;
import com.weeklab.weekbank.entities.DTOs.mappers.DepositMapper;
import com.weeklab.weekbank.entities.DTOs.mappers.UserMapper;
import com.weeklab.weekbank.entities.Deposit;
import com.weeklab.weekbank.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/deposits")
public class DepositResource {

    @Autowired
    private DepositService service;


    @GetMapping("/all")
    public ResponseEntity<List<DepositDTO>> findAll(){

        return ResponseEntity.ok().body(DepositMapper.INSTANCE.depositsToDtos((service.findAll())));
    }




  /*  @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
