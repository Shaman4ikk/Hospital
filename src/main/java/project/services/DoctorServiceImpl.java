package project.services;

import org.springframework.transaction.annotation.Transactional;
import project.dto.DoctorDTO;
import project.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.mappers.DoctorMapper;
import project.repositories.DoctorRepository;
import project.servicesentity.DoctorService;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMappers){
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMappers;
    }

    @Override
    @Transactional
    public List<DoctorDTO> getDoctors() {
        return doctorMapper.doctorsToDTO(doctorRepository.findAll());
    }

    @Override
    @Transactional
    public DoctorDTO getDoctorById(Long id){
        return doctorMapper.doctorToDTO(doctorRepository.getById(id));
    }

    @Override
    @Transactional
    public DoctorDTO create(DoctorDTO doctor){
        List<Doctor> doctors;
        doctors = doctorRepository.findAll();
        doctor.setId((long) doctors.size()+1);
        doctorRepository.save(doctorMapper.dtoToDoctor(doctor));
        return doctor;
    }

    @Override
    @Transactional
    public void update(DoctorDTO doctor){
        if(doctorRepository.existsById(doctor.getId())){
            doctorRepository.save(doctorMapper.dtoToDoctor(doctor));
        }
    }

    @Override
    @Transactional
    public void delete(DoctorDTO doctor){
        doctorRepository.delete(doctorMapper.dtoToDoctor(doctor));
    }
}
