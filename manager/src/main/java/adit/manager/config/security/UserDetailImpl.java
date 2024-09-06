package adit.manager.config.security;

import adit.manager.dao.ManagerRepository;
import adit.manager.entities.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserDetailImpl implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Autowired
    private ManagerRepository managerRepository;

    public static UserDetailImpl build(Manager manager){
        List<GrantedAuthority> authorities = manager.getRoles().stream()
                .map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return new UserDetailImpl(manager.getEmail(), manager.getPassword(), authorities);
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

}
