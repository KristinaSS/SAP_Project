package sapproject.project.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sapproject.project.models.Account;
import sapproject.project.models.Role;

import java.util.*;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(Account account) {
        this.username = account.getEmail();
        this.password = account.getPassword();
        this.active = true;
        ArrayList<String> roles = new ArrayList<>();

        account.getAccountType()
                .getRoleList()
                .forEach(role-> roles.add(role.getName()));

        this.authorities = Arrays
                .stream(roles.toArray(new String[roles.size()]))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public MyUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
