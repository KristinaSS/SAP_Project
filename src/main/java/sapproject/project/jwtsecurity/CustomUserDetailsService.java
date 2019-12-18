package sapproject.project.jwtsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sapproject.project.models.Account;
import sapproject.project.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByEmail(username);

        if (account == null) {
            throw new UsernameNotFoundException
                    ("User not found for this username :: " + username);
        }
        return UserPrincipal.create(account);
    }

    public UserDetails loadUserById(int id) {
        Account client = accountRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(client);
    }
}

