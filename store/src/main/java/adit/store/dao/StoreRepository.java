package adit.store.dao;

import adit.store.entities.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreRepository extends MongoRepository<Store, Long> {

    public List<Store> findByManagerReference(Long managerReference);

}
