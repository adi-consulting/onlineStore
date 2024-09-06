package adit.manager.dao;

import adit.manager.entities.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends MongoRepository<Manager, Long> {


    public Optional<Manager> findLastByOrderByCreationDateDesc();

    public Optional<Manager> findByEmail(String userName);
}
