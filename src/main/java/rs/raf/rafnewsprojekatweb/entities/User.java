package rs.raf.rafnewsprojekatweb.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

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
    @NotNull(message = "password field is required")
    @NotEmpty(message = "password field is required")
    private String password;

    public User(){

    }

    public User(String email, String firstName, String lastName, String type, Boolean active, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.active = active;
        this.password = password;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
