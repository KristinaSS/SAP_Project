package sapproject.project.services.classes;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.models.Account;
import sapproject.project.models.AccountType;
import sapproject.project.payload.UpdateAccount;
import sapproject.project.repository.AccountRepository;
import sapproject.project.repository.AccountTypeRepository;
import sapproject.project.services.interfaces.IAccountService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getOne(int Id) {
        return accountRepository.findById(Id)
                .orElseGet(() -> {
                    try {
                        throw new EntityNotFoundException(Account.class);
                    } catch (EntityNotFoundException e) {
                        //log.warn("Account not found: {}", Id);
                    }
                    return null;
                });
    }

    @Override
    public void deleteByID(int ID) {
        Account account = getOne(ID);
        if (account == null) {
            try {
                throw new EntityNotFoundException(Account.class);
            } catch (EntityNotFoundException e) {
                //log.warn("Account not found: {}", ID);
            }
            return;
        }
        accountRepository.delete(account);
        //log.debug("Deleted account with id: " + ID);
    }
    public Account updateAccount(UpdateAccount payload){
        Account account = findAccountByEmail(payload.getUsername(), true);
        AccountType accountType = accountTypeService.findAccountTypeByName(payload.getAccountTypeName());
        Account result = updateAccMembers(account, payload.getName(),payload.getUsername(), payload.getPassword(), accountType);
        result.setAccID(account.getAccID());

        return accountRepository.save(result);
    }

    @Deprecated
    @Override
    public Account updateByID(int ID, Account updatedAccount) {
       /* return accountRepository.findById(ID)
                .map(account -> accountRepository.save(updateAccMembers(account, updatedAccount)))
                .orElseGet(() -> {
                    updatedAccount.setAccID(ID);
                    //log.debug("New account has been created with ID: {}", ID);
                    return accountRepository.save(updatedAccount);
                });*/
       return null;
    }

    @Override
    public Account createOne(Account account, int accType) {

        AccountType accountType = accountTypeRepository.getOne(accType);

        account.setAccountType(accountTypeRepository.getOne(accType));
        return accountRepository.save(account);
    }

    //todo fix this
    @Override
    @Deprecated
    public Account createOne(Account entity) {
        return null;
    }

    private Account updateAccMembers(Account hashPassAcc, String name, String email, String password, AccountType accountType) {
        hashPassAcc.setName(name);
        hashPassAcc.setAccountType(accountType);
        hashPassAcc.setEmail(email);
        if(password!= null)
            hashPassAcc.setPassword( passwordEncoder.encode(password));
        return hashPassAcc;
    }

    public Account findAccountByEmail(String email) {
        System.out.println("find string: " + email);
        String username = email.substring(13, email.length()-2);
        System.out.println("username: "+ username);
        for (Account account : accountRepository.findAll()) {
            if (account.getEmail().equals(username))
                return account;
        }
        return null;
    }
    public Account findAccountByEmail(String email, boolean res) {
        for (Account account : accountRepository.findAll()) {
            if (account.getEmail().equals(email))
                return account;
        }
        return null;
    }
}
