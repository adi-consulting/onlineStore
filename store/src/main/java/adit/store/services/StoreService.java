package adit.store.services;

import adit.store.entities.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    public List<Store> getManagedStores(Long managerStore);

    public Store addStore(Store store);

    public void deleteStore(Long storeReference);


}
