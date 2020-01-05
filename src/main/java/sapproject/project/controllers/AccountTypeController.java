package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.AccountType;
import sapproject.project.services.classes.AccountTypeService;
import sapproject.project.services.interfaces.IAccountTypeService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("")
public class AccountTypeController {
    @Autowired
    AccountTypeService iAccountTypeService;

}
