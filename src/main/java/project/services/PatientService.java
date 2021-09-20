package project.services;

import project.dto.PatientDTO;

import java.util.List;

public interface PatientService {

    List<PatientDTO> getPatients();
    PatientDTO getPatientById(Long id);
    PatientDTO create(PatientDTO patient);
    boolean update(PatientDTO patient);
    boolean delete(PatientDTO patient);
}
