package adit.manager.services;

import adit.manager.entities.Manager;
import org.springframework.stereotype.Service;

public interface ManagerService {

    public Manager createManager(Manager manager);

    public void deleteManager(Long managerId);

    public void addRole(Manager manager, String role);

    public void deleteRole(Manager manager, String role);

    public void addToStore(Manager manager, String usc);

    public void deleteToStore(Manager manager, String usc);
}
