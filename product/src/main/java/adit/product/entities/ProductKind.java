package adit.product.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ProductKind {
    @Id
    private Long id;
    private String kind;
    private String description;
    private Long storeReference;

    private List<Map<String,String>> products;

}
