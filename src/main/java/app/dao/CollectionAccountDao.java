package app.dao;
import app.model.Account;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
public
class CollectionAccountDao implements Dao<Account> {
    private final List<Account> accounts;
    private long idCounter;

    public CollectionAccountDao (){
        this.accounts = new ArrayList<>();
        this.idCounter = 1;
    }
    public Account findByNumber(String number) {
        return this.accounts.stream()
                .filter(a -> a.getNumber().equals(number))
                .findAny()
                .orElseThrow(IllegalAccessError::new);
    }
    @Override
    public boolean deleteById(long id) {
        return this.accounts.removeIf(el -> el.getId().equals(id));
    }
    @Override
    public Account save(Account account) {
        if(account.getId() == null) {
            account.setId(idCounter++);
        } else {
            this.deleteById(account.getId());
        }
        this.accounts.add(account);
        return account;
    }
    @Override
    public boolean delete(Account account) {
       return this.accounts.remove(account);
    }
    @Override
    public void deleteAll(List<Account> entities) {
      this.accounts.removeAll(entities);
    }
    @Override
    public void saveAll(List<Account> entities) {
        entities.forEach(ent->{
            if (ent.getId() == null){
                ent.setId(idCounter++);
            } else {
                this.deleteById(ent.getId());
            }
            this.accounts.add(ent);
        });
    }
    @Override
    public List<Account> findAll() {
        return this.accounts;
    }
    @Override
    public Account getOne(long id) {
        return accounts.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
