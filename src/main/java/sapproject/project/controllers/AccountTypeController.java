package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.AccountType;
import sapproject.project.services.interfaces.IAccountTypeService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("")
public class AccountTypeController {
    @Autowired
    IAccountTypeService accountTypeService;

    @GetMapping("/all-account-types")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountType> getAllAccountTypes() {
        log.debug("REST request to get all AccountTypes");
        return accountTypeService.findAll();
    }
    @GetMapping("/accountType-{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountType getAccount(@PathVariable(name = "id") int id){
        log.debug("REST request to get Account Type: {}", id);
        return  accountTypeService.getOne(id);
    }

    @PostMapping("/accountType")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountType createAccountType(@Valid @RequestBody AccountType accountType) {
        log.debug("REST request to save Account Type : {}", accountType);
        return accountTypeService.createOne(accountType);
    }
}