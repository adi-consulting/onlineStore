package adit.manager.config.security;

import adit.manager.dao.ManagerRepository;
import adit.manager.entities.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ManagerRepository managerRepository;

    /**
     *
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Manager with username "+username+ " not found"));

        return UserDetailImpl.build(manager);
    }
}
