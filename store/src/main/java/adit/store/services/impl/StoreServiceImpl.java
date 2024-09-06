package adit.store.services.impl;

import adit.store.dao.StoreRepository;
import adit.store.entities.Store;
import adit.store.exceptions.NoFoundException;
import adit.store.services.StoreService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Slf4j
public class StoreServiceImpl implements StoreService {

    @Autowired
    public StoreRepository storeRepository;

    @Override
    public List<Store> getManagedStores(Long managerReference) {
        return storeRepository.findByManagerReference(managerReference);
    }

    @Override
    public Store addStore(Store store) {
        return  storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long idStore) {
        Optional<Store> optionalStore = storeRepository.findById(idStore);
        optionalStore.ifPresentOrElse(s-> storeRepository.delete(s),()-> log.warn("Store with id %s not found", idStore));
    }
}
