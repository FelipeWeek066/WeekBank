package com.weeklab.weekbank.resources;

import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody User user){
        service.insert(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
