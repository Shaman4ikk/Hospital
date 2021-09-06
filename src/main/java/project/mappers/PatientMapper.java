package project.mappers;

import org.mapstruct.Mapper;
import project.dto.PatientDTO;
import project.entity.Patient;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class, PatientInfoMapper.class, MedicineMapper.class})
public interface PatientMapper {
    PatientDTO patientToDTO(Patient patient);
    Patient dtoToPatient(PatientDTO patientDTO);
    List<PatientDTO> patientsToDTO(List<Patient> patients);
}
