package project.mappers;

import org.mapstruct.Mapper;
import project.dto.DoctorDTO;
import project.entity.Doctor;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDTO doctorToDTO(Doctor doctor);
    Doctor dtoToDoctor(DoctorDTO doctorDTO);
    List<DoctorDTO> doctorsToDTO(List<Doctor> doctorList);
}
