package project.services;

import project.dto.PatientInfoDTO;

import java.util.List;

public interface PatientInfoService {

    List<PatientInfoDTO> getPatientsInfo();
    PatientInfoDTO getPatientInfoById(Long id);
    PatientInfoDTO create(PatientInfoDTO patientInfo);
    void update(PatientInfoDTO patientInfo);
    void delete(Long id);
}
