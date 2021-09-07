package project.services;

import project.dto.DoctorDTO;
import project.entity.Doctor;

import java.util.List;

public interface DoctorService {

    List<DoctorDTO> getDoctors();
    DoctorDTO getDoctorById(Long id);
    Doctor create(DoctorDTO doctorDTO);
    void update(DoctorDTO doctorDTO);
    void delete(DoctorDTO doctorDTO);
}
