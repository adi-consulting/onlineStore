package adit.manager.services;

import adit.manager.dao.ManagerRepository;
import adit.manager.dto.ManagerAuthDto;
import adit.manager.dto.ManagerLoginDto;
import adit.manager.entities.Address;
import adit.manager.entities.Manager;
import adit.manager.exceptions.ManagerAldreadyExistException;
import adit.manager.models.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthentService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Manager signup(ManagerAuthDto managerAuthDto) throws ManagerAldreadyExistException {

        if(! managerRepository.findByEmail(managerAuthDto.getEmail()).isPresent()){//email not exist in database
            Manager manager = new Manager();
            manager.setEmail(managerAuthDto.getEmail());
            manager.setFirstName(managerAuthDto.getFirstName());
            manager.setLastName(managerAuthDto.getLastName());
            manager.setPassword(passwordEncoder.encode(managerAuthDto.getPassword()));
            Address address = new Address(managerAuthDto.getCity(), managerAuthDto.getStreetNumber(),managerAuthDto.getStreetNane());
            manager.setAddress(address);
            //role
            List<String> roles = new ArrayList<>();
            roles.add(RolesEnum.SUPERVISOR.getValue());
            manager.setRoles(roles);
            return managerService.createManager(manager);

        }else{
            throw new IllegalArgumentException("Manager with email "+managerAuthDto.getEmail()+ " already exist");
        }


    }


    public UserDetails authenticate(ManagerLoginDto managerLoginDto) {
        Authentication authentication=  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                managerLoginDto.getEmail(),
                managerLoginDto.getPassword()
        ));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails;
    }


}
