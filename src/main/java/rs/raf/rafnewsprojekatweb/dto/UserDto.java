package rs.raf.rafnewsprojekatweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String type;

    private Boolean active;


    public UserDto() {
    }

    public UserDto(String email, String firstName, String lastName, String type, Boolean active) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
