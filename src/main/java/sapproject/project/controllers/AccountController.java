package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Account;
import sapproject.project.models.Cart;
import sapproject.project.payload.UpdateAccount;
import sapproject.project.repository.CartRepository;
import sapproject.project.services.classes.AccountService;
import sapproject.project.services.interfaces.IAccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    CartRepository cartRepository;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        //log.debug("REST request to get all Accounts.");
        return accountService.findAll();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@Valid @RequestBody UpdateAccount account)
    {
        //log.debug("REST request to update Account : {}", ID);
        return accountService.updateAccount(account);
    }

    @GetMapping("/delete-{accID}")
    @ResponseStatus(HttpStatus.OK)
    public Account deleteAccount(@PathVariable(value = "accID") Integer accID) {
        //log.debug("REST request to delete Account : {}", accID);
        return accountService.deleteByID(accID);
    }
}
