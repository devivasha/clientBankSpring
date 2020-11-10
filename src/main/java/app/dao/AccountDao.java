package app.dao;

import app.service.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AccountDao implements Dao<Account>{
    private final List<Account> accounts = new ArrayList<>();
    private long idCounter = 0;

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public boolean delete(Account account) {
        return false;
    }

    @Override
    public void deleteAll(List<Account> entities) {


    }

    @Override
    public void saveAll(List<Account> entities) {

    }

    @Override
    public List<Account> findAll() {
        return Collections.unmodifiableList(this.accounts);
    }

    @Override
    public boolean deleteById(long id) {
        return accounts.removeIf(el -> el.getId().equals(id));
    }

    @Override
    public Account getOne(long id) {
        return accounts.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
