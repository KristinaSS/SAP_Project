package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Account;
import sapproject.project.services.interfaces.IAccountService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("")
public class AccountController {
    @Autowired
    IAccountService accountService;

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        log.debug("REST request to get all Accounts.");
        return accountService.findAll();
    }

    @GetMapping("/account-{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable(name = "id") int id){
        log.debug("REST request to get Account : {}", id);
        return  accountService.getOne(id);
    }

    @PostMapping("/account/{typeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@Valid @RequestBody Account account, @PathVariable(value = "typeId") Integer typeId) {
        System.out.println("Enter 1");
        log.debug("REST request to save Account : {}", account);
        return accountService.createOne(account,typeId);
    }

    @PutMapping("/edit-acc-{accID}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable(value = "accID") Integer ID,
    @Valid @RequestBody Account account){
        log.debug("REST request to update Account : {}", ID);
        return accountService.updateByID(ID,account);
    }

    @DeleteMapping("/delete-account-{accID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFoodplace(@PathVariable(value = "accID") Integer accID) {
        log.debug("REST request to delete Account : {}", accID);
        accountService.deleteByID(accID);
    }
}