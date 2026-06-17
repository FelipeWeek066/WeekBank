package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.DTOs.DepositDTO;
import com.weeklab.weekbank.entities.DTOs.mappers.DepositMapper;
import com.weeklab.weekbank.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deposits")
public class DepositResource {

    @Autowired
    private DepositService service;


    @GetMapping()
    public ResponseEntity<List<DepositDTO>> findAll(){

        return ResponseEntity.ok().body(DepositMapper.INSTANCE.depositsToDTOs((service.findAll())));
    }




  /*  @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
