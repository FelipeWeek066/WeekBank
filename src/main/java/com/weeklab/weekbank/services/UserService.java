package com.weeklab.weekbank.services;

import com.weeklab.weekbank.entities.Deposit;
import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.repositories.DepositRepository;
import com.weeklab.weekbank.repositories.UserRepository;
import com.weeklab.weekbank.services.exceptions.ContentNotFoundException;
import com.weeklab.weekbank.services.exceptions.MinimumBudgetTransferenceException;
import com.weeklab.weekbank.services.exceptions.NotEnoughMoneyException;
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
        if(deposit.getAmount() < 1) {
            throw new MinimumBudgetTransferenceException("low quantity.");
        }

        if (deposit.getPayer().getAmount() <= deposit.getAmount() ) {
            throw new NotEnoughMoneyException("no money.");
        }


        User payer = deposit.getPayer();
        User payee = deposit.getPayee();
        payer.remAmount(deposit.getAmount());
        payee.addAmount(deposit.getAmount());
        repository.save(payer);
        repository.save(payee);

        return depositRepository.save(deposit);


    }

}
