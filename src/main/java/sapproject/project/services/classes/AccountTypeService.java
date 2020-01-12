package sapproject.project.services.classes;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.ListSizeIsZero;
import sapproject.project.models.AccountType;
import sapproject.project.repository.AccountTypeRepository;
import sapproject.project.services.interfaces.IAccountTypeService;

import java.util.List;

@Log4j2
@Service
public class AccountTypeService implements IAccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public List<AccountType> findAll() {
        List<AccountType>accountTypes =  accountTypeRepository.findAll();
        if (accountTypes.size() == 0)
            throw new ListSizeIsZero("accountTypes");
        else
            return accountTypes;
    }

    @Override
    public AccountType findAccountTypeByName(String name) {
        for (AccountType accountType : accountTypeRepository.findAll()) {
            if (accountType.getName().equals(name))
                return accountType;
        }
        return null;
    }
}
