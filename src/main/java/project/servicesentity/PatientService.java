package project.servicesentity;

import project.dto.PatientDTO;

import java.util.List;

public interface PatientService {

    List<PatientDTO> getPatients();
    PatientDTO getPatientById(Long id);
    void create(PatientDTO patient);
    void update(PatientDTO patient);
    void delete(PatientDTO patient);
}
