package adit.manager.services.impl;

import adit.manager.dao.ManagerRepository;
import adit.manager.entities.Manager;
import adit.manager.services.ManagerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager createManager(Manager manager) {
        manager.setUmc(generateUmc());
        return managerRepository.save(manager);
    }

    @Override
    public void deleteManager(Long managerId) {
       Optional<Manager> optionalManager = managerRepository.findById(managerId);
       optionalManager.ifPresentOrElse(m -> managerRepository.delete(m),()-> log.info("manager  with id %s not exist", managerId));
    }

    @Override
    public void addRole(Manager manager,  String role) {
            List<String> roles = manager.getRoles();
            if(!roles.contains(role)){
               roles.add(role);
                managerRepository.save(manager);
            }
    }

    @Override
    public void deleteRole(Manager manager, String role) {
        List<String> roles = manager.getRoles();
        if(!roles.contains(role)){
            roles.remove(role);
            managerRepository.save(manager);
        }
    }

    @Override
    public void addToStore(Manager manager, String usc) {
        if(manager.getUscStores() == null){
            manager.setUscStores(new ArrayList<>());
        }
        if(!manager.getUscStores().contains(usc)){

            manager.getUscStores().add(usc);
            managerRepository.save(manager);
        }

    }

    @Override
    public void deleteToStore(Manager manager, String usc) {
        if(manager.getUscStores() != null && manager.getUscStores().contains(usc)){
            manager.getUscStores().remove(usc);
        }

    }

    private String generateUmc(){
        Optional<Manager> optionalManager = managerRepository.findLastByOrderByCreationDateDesc();
        if(optionalManager.isPresent()){
            return String.format("%09d", Integer.parseInt(optionalManager.get().getUmc())+1);

        }else{
            return String.format("%09d",1);
        }
    }


}
