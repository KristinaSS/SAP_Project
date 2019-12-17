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
    IAccountTypeService iAccountTypeService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountType> getAllAccountTypes() {
        //log.debug("REST request to get all Order Details.");
        return iAccountTypeService.findAll();
    }

    @GetMapping("/get-{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountType getAccountType(@PathVariable(name = "id") int id){
        //log.debug("REST request to get Order Details : {}", id);
        return  iAccountTypeService.getOne(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountType createAccountType(@Valid @RequestBody AccountType orderDetails) {
        //log.debug("REST request to save Order Details : {}", orderDetails);
        return iAccountTypeService.createOne(orderDetails);
    }

    @PutMapping("/edit-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public AccountType updateAccountType(@PathVariable(value = "ID") Integer ID,
                                      @Valid @RequestBody AccountType orderDetails){
        //log.debug("REST request to update Order Details : {}", ID);
        return iAccountTypeService.updateByID(ID,orderDetails);
    }

    @DeleteMapping("/delete-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccountType(@PathVariable(value = "ID") Integer ID) {
        //log.debug("REST request to delete Order Details : {}", ID);
        iAccountTypeService.deleteByID(ID);
    }
}