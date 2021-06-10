package teixerya.e2.repository;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {

    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customer_id;

    @Column(name = "first_name")
    private String firstName = "";

    @Column(name = "last_name")
    private String lastName = "";

    @Column(name = "email")
    private String email = "";

    @Column(name = "street")
    private String street = "";

    @Column(name = "city")
    private String city = "";

    @Column(name = "state")
    private String state = "";

    @Column(name = "zip_code")
    private String zipCode = "";


    public CustomerEntity(){
    };

    public Integer getId() {
        return customer_id;
    }

    public void setId(Integer id) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

