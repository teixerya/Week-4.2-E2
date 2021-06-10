package teixerya.e2.repository;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class StudentEntity implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName = "";

    @Column(name = "last_name")
    private String lastName = "";

    @Column(name = "program_name")
    private String programName = "";

    @Column(name = "program_year")
    private Integer programYear = 0;

    @Column(name = "program_coop")
    private Boolean programCoop = false;

    @Column(name = "program_internship")
    private Boolean programInternship = false;

    public StudentEntity(){
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Integer getProgramYear() {
        return programYear;
    }

    public void setProgramYear(Integer programYear) {
        this.programYear = programYear;
    }

    public Boolean isProgramCoop() {
        return programCoop;
    }

    public void setProgramCoop(Boolean programCoop) {
        this.programCoop = programCoop;
    }

    public Boolean isProgramInternship() {
        return programInternship;
    }

    public void setProgramInternship(Boolean programInternship) {
        this.programInternship = programInternship;
    }
}

