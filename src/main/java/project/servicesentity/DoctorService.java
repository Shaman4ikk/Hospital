package project.servicesentity;

import project.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    List<DoctorDTO> getDoctors();
    DoctorDTO getDoctorById(Long id);
    DoctorDTO create(DoctorDTO doctorDTO);
    void update(DoctorDTO doctorDTO);
    void delete(DoctorDTO doctorDTO);
}
