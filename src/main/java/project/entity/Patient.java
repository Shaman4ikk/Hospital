package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    private Doctor doctor;


    private PatientInfo patientInfo;

    private List<Medicine> medicine;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "patients_medicine",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id",
                    referencedColumnName = "id"))
    public List<Medicine> getMedicine() {
        return medicine;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_info")
    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    @Id
    public Long getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctors_id")
    public Doctor getDoctor() {
        return doctor;
    }

}
