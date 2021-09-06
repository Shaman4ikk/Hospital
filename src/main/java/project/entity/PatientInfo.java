package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "patient_info")
@Data
public class PatientInfo {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "info")
    private String info;

    @Id
    public Long getId() {
        return id;
    }

}
