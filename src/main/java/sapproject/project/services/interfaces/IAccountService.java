package sapproject.project.services.interfaces;

import sapproject.project.models.Account;

public interface IAccountService extends Service<Account> {
    Account createOne(Account account, int accType);
}
