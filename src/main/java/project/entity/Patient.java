package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator = "patient_sq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "patient_sq", strategy = "increment")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private Doctor doctor;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn(name = "patient_info", referencedColumnName = "id")
    private PatientInfo patientInfo;

    @ManyToMany
    @JoinTable(name = "patients_medicine",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id",
                    referencedColumnName = "id"))
    private List<Medicine> medicine;

}
