package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "medicines")
@Data
public class Medicine {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "medicine_name")
    private String medicineName;

    @Id
    public Long getId() {
        return id;
    }
}
