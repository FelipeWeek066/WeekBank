package com.weeklab.weekbank.services;

import com.weeklab.weekbank.entities.UserCode;
import com.weeklab.weekbank.repositories.UserCodeRepository;
import com.weeklab.weekbank.repositories.UserRepository;
import com.weeklab.weekbank.services.exceptions.ContentNotFoundException;
import com.weeklab.weekbank.services.exceptions.PendingCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCodeService {
    @Autowired
    private UserCodeRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<UserCode> findAll() {
        return repository.findAll();
    }

    public UserCode findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ContentNotFoundException(id));
    }

    public UserCode findByName(String name) {

        return repository.findByName(name).orElseThrow(() -> new ContentNotFoundException(name));

    }

    public UserCode insert(UserCode userCode){

        //well it works
        if(repository.existsByName(userCode.getName())){
            throw new PendingCodeException("there is a pending code for that name");
        }
        if(userRepository.existsByName(userCode.getName())){
            throw new PendingCodeException("there is already a user with that name");

        }
        //create code
        StringBuilder Code = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            Code.append((int) (Math.random() * 10));
        }
        userCode.setCode(Code.toString());
        while(repository.existsByCode(userCode.getCode())) {
            for(int i = 0; i < 4; i++) {
                Code.append((int) (Math.random() * 10));
            }
            userCode.setCode(Code.toString());

        }
        return repository.save(userCode);
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }
}
