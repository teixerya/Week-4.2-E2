package teixerya.e2.model;

import com.sun.istack.NotNull;

import javax.validation.constraints.*;
import java.io.Serializable;

public class StudentForm implements Serializable {

    private int customer_id = 0;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String firstName = "";

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String lastName = "";


    private String email = "";

    @NotBlank
    private String street = "";

    private boolean programCoop = false;

    private boolean programInternship = false;

    public StudentForm() {
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isProgramCoop() {
        return programCoop;
    }

    public void setProgramCoop(boolean coop) {
        this.programCoop = coop;
    }

    public boolean isProgramInternship() {
        return programInternship;
    }

    public void setProgramInternship(boolean internship) {
        this.programInternship = internship;
    }


}

