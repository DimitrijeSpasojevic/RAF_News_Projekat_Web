package rs.raf.rafnewsprojekatweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserUpdateDto {


    @NotNull(message = "Id field is required")
    private Integer id;
    @NotNull(message = "email field is required")
    @NotEmpty(message = "email field is required")
    private String email;
    @NotNull(message = "firstName field is required")
    @NotEmpty(message = "firstName field is required")
    private String firstName;
    @NotNull(message = "lastName field is required")
    @NotEmpty(message = "lastName field is required")
    private String lastName;
    @NotNull(message = "type field is required")
    @NotEmpty(message = "type field is required")
    private String type;
    @NotNull(message = "active field is required")
    private Boolean active;

    public UserUpdateDto(Integer id, String email, String firstName, String lastName, String type) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public UserUpdateDto(Integer id, String email, String firstName, String lastName, String type, Boolean active) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserUpdateDto() {
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
