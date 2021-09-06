package project.servicesentity;

import project.dto.PatientInfoDTO;

import java.util.List;

public interface PatientInfoService {

    List<PatientInfoDTO> getPatientsInfo();
    PatientInfoDTO getPatientInfoById(Long id);
    void create(PatientInfoDTO patientInfo);
    void update(PatientInfoDTO patientInfo);
    void delete(PatientInfoDTO patientInfo);
}
