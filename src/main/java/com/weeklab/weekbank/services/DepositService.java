package com.weeklab.weekbank.services;

import com.weeklab.weekbank.entities.Deposit;
import com.weeklab.weekbank.entities.User;
import com.weeklab.weekbank.repositories.DepositRepository;
import com.weeklab.weekbank.services.exceptions.ContentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DepositService {
    @Autowired
    private DepositRepository repository;
    @Autowired
    private UserService userService;
    public List<Deposit> findAll(){
        return repository.findAll();
    }
    public List<Deposit> findByUser(User user){
        return new ArrayList<>(repository.findByPayerOrPayee(user, user));
    }

    public Deposit findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ContentNotFoundException(id));
    }


    public Deposit insert(Deposit deposit) {

        return repository.save(deposit);
    }
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }
//no update in a transaction, they're immutable
/*    public Transaction update(UUID id, Transaction obj) {
        Transaction transaction = findById(id);
        updateData(transaction, obj);
        return repository.save(user);
    }
*/

}
