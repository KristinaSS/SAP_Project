package sapproject.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sapproject.project.jwtsecurity.JwtTokenProvider;
import sapproject.project.models.Account;
import sapproject.project.models.AccountType;
import sapproject.project.payload.ApiResponse;
import sapproject.project.payload.JwtAuthenticationResponse;
import sapproject.project.payload.LoginRequest;
import sapproject.project.payload.SignUpRequest;
import sapproject.project.repository.AccountRepository;
import sapproject.project.repository.AccountTypeRepository;
import sapproject.project.services.interfaces.IAccountService;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class AuthController {
    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    IAccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("enter authentication backend");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if(accountRepository.existsAccountByEmail(signUpRequest.getUsername())) {
            System.out.println("Username is already taken!");
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating client account
        String email = signUpRequest.getUsername();
        String pass = passwordEncoder.encode(signUpRequest.getPassword());
        String name  = signUpRequest.getName();
        AccountType accountType = accountTypeRepository.getAccountTypeByAccountTypeID(1);

        Account client = new Account(accountType,name, email, pass);

        Account result = accountRepository.save(client);

        //accountService.createOne(client,1);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/account/create-1")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
/*
    @PostMapping("/findAccountByEmail")
    public boolean existsByEmail(@Valid @RequestBody String email){
        boolean result =  accountRepository.existsAccountByEmail(email);
        System.out.println("result: "+ result);
        return result;
    }*/
}

