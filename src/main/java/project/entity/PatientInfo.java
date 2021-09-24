package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "patient_info")
@Data
public class PatientInfo {

    @Id
    @GeneratedValue(generator = "patientInfo_sq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "patientInfo_sq", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "info")
    private String info;

}
