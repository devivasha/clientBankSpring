package app.service;

import app.dao.CollectionAccountDao;
import app.dto.AccountDto;
import app.model.Account;
import app.model.Currency;
import app.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class AccountService {
    private CollectionAccountDao collectionAccountDao;

    public AccountDto get(long id) {
        return new AccountDto(this.collectionAccountDao.getOne(id));
    }

    public List<Account> getAll() {
        return this.collectionAccountDao.findAll();
    }

    public Account create(Currency currency, Customer customer) {
        return this.collectionAccountDao.save(new Account(currency, customer));
    }

    public boolean remove(long id) {
        return this.collectionAccountDao.deleteById(id);
    }
    public boolean replenish (long id, double amount) {
        try {
            Account account = this.collectionAccountDao.getOne(id);
            account.setBalance(account.getBalance() + amount);
            this.collectionAccountDao.save(account);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean withdraw(long id, double amount) {
        try {
            Account account = this.collectionAccountDao.getOne(id);
            if (account.getBalance() < amount) throw new IllegalArgumentException("money shortage");
            account.setBalance(account.getBalance() - amount);
            this.collectionAccountDao.save(account);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    public boolean transfer(Long to, Long from, Double amount) {
        try {
            Account accTo = this.collectionAccountDao.getOne(to);
            Account accFrom = this.collectionAccountDao.getOne(from);
            if (accFrom.getBalance() < amount) throw new IllegalArgumentException("money shortage");
            accFrom.setBalance(accFrom.getBalance() - amount);
            accTo.setBalance(accTo.getBalance() + amount);
            this.collectionAccountDao.saveAll(List.of(accTo, accFrom));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
