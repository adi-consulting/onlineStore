package adit.manager.dto;

import lombok.Data;

@Data
public class ManagerAuthDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    //adress
    private String city;
    private String streetNumber;
    private String StreetNane;

}
