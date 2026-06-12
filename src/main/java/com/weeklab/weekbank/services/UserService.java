package com.weeklab.weekbank.services;

import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.repositories.UserRepository;
import com.weeklab.weekbank.services.exceptions.ContentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ContentNotFoundException(id));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username);
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(UUID id, User obj) {
        User user = findById(id);
        updateData(user, obj);
        return repository.save(user);
    }

    private void updateData(User user, User obj) {
        user.setName(obj.getName());
    }
}
