package sapproject.project.services.interfaces;

import sapproject.project.models.Account;
import sapproject.project.models.AccountType;
import sapproject.project.payload.UpdateAccount;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account getOne(int Id);

    Account deleteByID(int ID);

    Account updateAccount(UpdateAccount payload);

    Account findAccountByEmail(String email);

    Account findAccountByEmail(String email, boolean res);
}
