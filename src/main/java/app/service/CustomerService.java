package app.service;
import app.dao.Dao;
import app.dto.CustomerDto;
import app.model.Account;
import app.model.Customer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final Dao<Customer> customerDao;
    private final AccountService accountService;

    public CustomerService(Dao<Customer> customerDao, AccountService accountService) {
        this.customerDao = customerDao;
        this.accountService = accountService;
    }

    public CustomerDto get (long id) {
        return new CustomerDto(this.customerDao.getOne(id));
    }
    public Customer getCustomer (long id) {
        return this.customerDao.getOne(id);
    }

    public List<CustomerDto> getAll() {
        return customerDao.findAll().stream()
                .reduce(new ArrayList<CustomerDto>(), (arr, c) -> {
                    arr.add(new CustomerDto(c));
                    return arr;
                }, (a, b) -> a);
    }
    public CustomerDto create (Customer customer) {
        return new CustomerDto(customerDao.save(customer));
    }
    public void delete (long id) {
        this.customerDao.deleteById(id);
    }

    public CustomerDto update (long id) {
        Customer customer = this.customerDao.getOne(id);
        return new CustomerDto(this.customerDao.save(customer));
    }

    public boolean removeAccount(long id, long accId) {
        if (this.accountService.remove(accId)) {
            Customer customer = this.customerDao.getOne(id);
            List<Account> accounts = customer.getAccounts().stream()
                    .filter(a -> a.getId() != accId)
                    .collect(Collectors.toList());
            customer.setAccounts(accounts);
            return true;
        }
        return false;
    }






}
