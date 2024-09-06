package adit.product.services.impl;

import adit.product.dao.ProductKindRepository;
import adit.product.entities.ProductKind;
import adit.product.models.Kind;
import adit.product.models.Product;
import adit.product.services.ProductService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Setter
@Getter
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductKindRepository productKindRepository;



    @Override
    public List<Kind> getAllStoreProductKind(Long storeReference) {
        return productKindRepository.findByStoreReference(storeReference);
    }

    @Override
    public ProductKind getProductKind(Long storeReference, String kind) {
        List<ProductKind> productKindList = productKindRepository.findByStoreReferenceAndkind(storeReference, kind);
        if(!productKindList.isEmpty()){
            return productKindList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public ProductKind createProductKind(ProductKind productKind) {
        return productKindRepository.save(productKind);
    }

    @Override
    public void addProduct(Product product, Long productKindReference) {
        Optional<ProductKind> optionalProductKind = productKindRepository.findById(productKindReference);
        optionalProductKind.ifPresentOrElse(p ->p.getProducts().add(product.getProduct()), ()->log.warn("ProductKind with id %s not found", productKindReference));

    }

    @Override
    public void removeProduct(int productOrder, Long productKindReference) {
        Optional<ProductKind> optionalProductKind = productKindRepository.findById(productKindReference);
        optionalProductKind.ifPresentOrElse(p ->p.getProducts().removeIf(p2 -> Integer.valueOf(p2.get("order"))==productOrder), ()->log.warn("ProductKind with id %s not found", productKindReference));
        
    }

    @Override
    public void removeProductKind(Long productKindReference) {
        Optional<ProductKind> optionalProductKind = productKindRepository.findById(productKindReference);
        optionalProductKind.ifPresentOrElse(p ->productKindRepository.delete(p), ()->log.warn("ProductKind with id %s not found", productKindReference));
    }
}
