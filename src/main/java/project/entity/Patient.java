package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Doctor doctor;

    private PatientInfo patientInfo;

    private List<Medicine> medicine;
    @ManyToMany
    @JoinTable(name = "patients_medicine",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id",
                    referencedColumnName = "id"))
    public List<Medicine> getMedicine() {
        return medicine;
    }

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn(name = "patient_info", referencedColumnName = "id")
    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "doctors_id")
    public Doctor getDoctor() {
        return doctor;
    }

}
