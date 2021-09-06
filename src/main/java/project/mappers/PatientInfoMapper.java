package project.mappers;

import org.mapstruct.Mapper;
import project.dto.PatientInfoDTO;
import project.entity.PatientInfo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientInfoMapper {
    PatientInfoDTO patientInfoToDTO(PatientInfo patientInfo);
    PatientInfo dtoToPatientInfo(PatientInfoDTO patientInfoDTO);
    List<PatientInfoDTO> patientsInfoToDTO(List<PatientInfo> patientInfoList);
}
