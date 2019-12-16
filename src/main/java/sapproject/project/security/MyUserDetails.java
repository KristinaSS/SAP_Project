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
        //List<String> roleList = new ArrayList<>();
        Role[] roles = account.getAccountType().getRoleList().toArray(new Role[account.getAccountType().getRoleList().size()]);
       /* for(Role role: )
            roles.append(role.getName()+ " ");*/
        /*this.authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());*/
    }

    public MyUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("client"));
    }

    @Override
    public String getPassword() {
        return "pass";
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
        return true;
    }
}
