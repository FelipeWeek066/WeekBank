package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.UserCode;
import com.weeklab.weekbank.services.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/codes")
public class UserCodeResource {
    @Autowired
    private UserCodeService service;
    @GetMapping
    public ResponseEntity<List<UserCode>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<UserCode> findByName(@PathVariable String name){
        return ResponseEntity.ok().body(service.findByName(name));
    }
    @PostMapping
    public ResponseEntity<UserCode> insert(@RequestBody UserCode userCode){
        userCode = service.insert(userCode);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCode.getId()).toUri();

        return ResponseEntity.created(uri).body(userCode);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
