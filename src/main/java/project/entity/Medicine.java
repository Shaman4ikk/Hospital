package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "medicines")
@Data
public class Medicine {

    @Id
    @GeneratedValue(generator = "doctor_sq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "doctor_sq", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "medicine_name")
    private String medicineName;

}
