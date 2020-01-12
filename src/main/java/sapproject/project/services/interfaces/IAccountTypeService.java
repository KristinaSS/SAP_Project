package sapproject.project.services.interfaces;

import sapproject.project.models.AccountType;

import java.util.List;

public interface IAccountTypeService{
    List<AccountType> findAll();

    AccountType findAccountTypeByName(String name);
}
