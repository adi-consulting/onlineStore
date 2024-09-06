package adit.store.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    private Long id;
    private Integer usc; //unique store code
    private long managerReference;
    private String name;
    private Address address;
    private Boolean activated;






}
