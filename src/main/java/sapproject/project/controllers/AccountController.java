package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Account;
import sapproject.project.payload.UpdateAccount;
import sapproject.project.services.interfaces.IAccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@Valid @RequestBody UpdateAccount account) {
        return accountService.updateAccount(account);
    }

    @GetMapping("/delete-{accID}")
    @ResponseStatus(HttpStatus.OK)
    public Account deleteAccount(@PathVariable(value = "accID") Integer accID) {
        return accountService.deleteByID(accID);
    }
}
