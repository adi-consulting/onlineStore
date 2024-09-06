package adit.product.services;

import adit.product.entities.ProductKind;
import adit.product.models.Kind;
import adit.product.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Kind> getAllStoreProductKind(Long storeReference);

    public ProductKind getProductKind(Long storeReference, String kind);

    public ProductKind createProductKind(ProductKind productKind);

    public void addProduct(Product product, Long productKindReference);

    public void removeProduct(int productOrder, Long productKindReference);

    public void removeProductKind(Long productKindReference);
}
