package sapproject.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sapproject.project.models.AccountType;

import java.util.List;


@RestController
public class HomeController {
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        //log.debug("REST request to get all Order Details.");
        return "Welcome home";
    }
}
