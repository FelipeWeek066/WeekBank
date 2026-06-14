package com.weeklab.weekbank.services;

import com.weeklab.weekbank.entities.Deposit;
import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.repositories.DepositRepository;
import com.weeklab.weekbank.repositories.UserRepository;
import com.weeklab.weekbank.resources.DepositResource;
import com.weeklab.weekbank.services.exceptions.ContentNotFoundException;
import com.weeklab.weekbank.services.exceptions.NotEnoughMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private DepositRepository depositRepository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ContentNotFoundException(id));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username).orElseThrow(() -> new ContentNotFoundException(""));
    }
    public User findByName(String name) {
        User user = (User)repository.findByName(name).orElseThrow(() -> new ContentNotFoundException(name));
        return user;

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

    public Deposit deposit(Deposit deposit) {
        if (deposit.getPayer().getAmount() >= deposit.getAmount()) {
            return depositRepository.save(deposit);
        }
            throw new NotEnoughMoneyException("no money.");
    }

}
