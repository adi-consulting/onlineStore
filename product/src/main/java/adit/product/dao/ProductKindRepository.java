package adit.product.dao;

import adit.product.entities.ProductKind;
import adit.product.models.Kind;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductKindRepository extends MongoRepository<ProductKind, Long> {

    public List<Kind> findByStoreReference(Long storeReference);

    //to do return one porductKind
    public List<ProductKind> findByStoreReferenceAndkind(Long storeReference, String kind);

}
