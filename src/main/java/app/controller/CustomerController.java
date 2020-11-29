package app.controller;

import app.dto.AccountDto;
import app.dto.CustomerDto;
import app.model.Account;
import app.model.Currency;
import app.model.Customer;
import app.service.AccountService;
import app.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/customers")
@RestController
public class CustomerController {
    private CustomerService customerService;
    private AccountService accountService;
    //    Получить информацию про отдельного пользователя включая его счета
    @GetMapping("{id}")
    public CustomerDto get(@PathVariable("id") long id) {
        return this.customerService.get(id);
    }
    //    Получить информацию про всех пользователей
    @GetMapping("")
    public List<CustomerDto> get() {
        return this.customerService.getAll();
    }
    //    Создать пользователя
    @PostMapping("")
    public CustomerDto create(@RequestBody CustomerDto c) {
        Customer customer = new Customer(c.getName(), c.getEmail(), c.getAge());
        return this.customerService.create(customer);
    }
    //    Изменить данные пользователя
    @PutMapping("{id}")
    public CustomerDto update(@PathVariable("id") long id, @RequestBody CustomerDto c) {
        return this.customerService.update(id);
    }
    //    Удалить пользователя
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        this.customerService.delete(id);
    }
    //    Создать счет для конкретного пользователя
    @PostMapping("{id}")
    public AccountDto createAccount(@PathVariable("id") long id, @RequestBody Currency currency) {
        Customer customer = this.customerService.getCustomer(id);
        Account account = this.accountService.create(currency, customer);
        customer.addAccount(account);
        return new AccountDto(account);
    }
    //    Удалить счет у пользователя
    @DeleteMapping("{id}/{accId}")
    public boolean removeAccount(@PathVariable("id") long id, @PathVariable("accId") long accId) {
        return this.customerService.removeAccount(id, accId);
    }

}
