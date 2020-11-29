package app.controller;

import app.dto.AccountDto;
import app.dto.TransactionDto;
import app.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AccountController {
    private AccountService accountService;

    @GetMapping("{id}")
    public AccountDto get(@PathVariable("id") long id){
        return this.accountService.get(id);
    }
    //    Пополнить счет
    @PostMapping("replenish")
    public boolean replenish(@RequestBody TransactionDto t) {
        return this.accountService.replenish(t.getId(), t.getAmount());
    }
    //    Снять деньги со счета
    @PostMapping("withdraw")
    public boolean withdraw(@RequestBody TransactionDto t) {
        return this.accountService.withdraw(t.getId(), t.getAmount());
    }
    //    Перевести деньги на другой счет
    @PostMapping("transfer")
    public boolean transfer(@RequestBody TransactionDto t) {
        return this.accountService.transfer(t.getDestination(), t.getId(), t.getAmount());
    }



}
