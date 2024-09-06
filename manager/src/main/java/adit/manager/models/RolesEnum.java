package adit.manager.models;

public enum RolesEnum {
    ADMIN("admin"),
    USER("user"),
    SUPERVISOR("supervisor");

    private final String value;

    private RolesEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
