package sapproject.project.services.classes;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.exceptions.ListSizeIsZero;
import sapproject.project.models.Account;
import sapproject.project.models.AccountType;
import sapproject.project.models.Cart;
import sapproject.project.payload.UpdateAccount;
import sapproject.project.repository.AccountRepository;
import sapproject.project.repository.AccountTypeRepository;
import sapproject.project.repository.CartRepository;
import sapproject.project.services.interfaces.IAccountService;

import java.util.List;

@Log4j2
@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;

    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.size() == 0)
            throw new ListSizeIsZero("accounts");
        else
            return accounts;
    }

    public Account getOne(int Id) {
        return accountRepository.findById(Id)
                .orElseGet(() -> {
                        throw new EntityNotFoundException(Account.class);
                });
    }

    @Override
    public Account deleteByID(int ID) {
        Account account = getOne(ID);
        if (account == null) {
                throw new EntityNotFoundException(Account.class);
        }
        Cart cart = cartService.findCart(ID);
        if (cart != null)
            cartRepository.delete(cart);
        accountRepository.delete(account);
        return account;
    }

    @Override
    public Account updateAccount(UpdateAccount payload) {
        Account account = getOne(Integer.parseInt(payload.getId()));
        AccountType accountType = null;
        if (payload.getAccountTypeName() != null) {
            accountType = accountTypeService.findAccountTypeByName(payload.getAccountTypeName());
        }
        Account result = updateAccMembers(account, payload.getName(), payload.getUsername(), payload.getPassword(), accountType);
        result.setAccID(account.getAccID());

        return accountRepository.save(result);
    }

    @Override
    public Account findAccountByEmail(String email) {
        System.out.println("find string: " + email);
        String username = email.substring(13, email.length() - 2);
        System.out.println("username: " + username);
        for (Account account : accountRepository.findAll()) {
            if (account.getEmail().equals(username))
                return account;
        }
        return null;
    }

    @Override
    public Account findAccountByEmail(String email, boolean res) {
        for (Account account : accountRepository.findAll()) {
            if (account.getEmail().equals(email))
                return account;
        }
        return null;
    }

    private Account updateAccMembers(Account hashPassAcc, String name, String email, String password, AccountType accountType) {
        hashPassAcc.setName(name);
        if (accountType != null) {
            hashPassAcc.setAccountType(accountType);
        }
        hashPassAcc.setEmail(email);
        if (password != null && !password.equals(" "))
            hashPassAcc.setPassword(passwordEncoder.encode(password));
        return hashPassAcc;
    }
}
