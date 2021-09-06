package project.dto;

import lombok.Data;

import java.util.List;

@Data
public class PatientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private DoctorDTO doctor;
    private PatientInfoDTO patientInfo;
    private List<MedicineDTO> medicine;
}
