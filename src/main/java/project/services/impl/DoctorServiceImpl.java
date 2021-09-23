package project.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import project.dto.DoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dto.PatientDTO;
import project.entity.Doctor;
import project.mappers.DoctorMapper;
import project.repositories.DoctorRepository;
import project.repositories.PatientRepository;
import project.services.DoctorService;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
//    private final PatientServiceImpl patientRepository;
    private final Logger logger = LogManager.getLogger(DoctorServiceImpl.class);

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMappers){
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMappers;
//        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public List<DoctorDTO> getDoctors() {
        logger.log(Level.INFO, "Start getting doctors");
        return doctorMapper.doctorsToDTO(doctorRepository.findAll());
    }

    @Override
    @Transactional
    public DoctorDTO getDoctorById(Long id){
        logger.log(Level.INFO, "Start getting doctor by id");
        return doctorMapper.doctorToDTO(doctorRepository.getById(id));
    }

    @Override
    @Transactional
    public Doctor create(DoctorDTO doctor){
        logger.log(Level.INFO, "Create new doctor");
        return doctorRepository.save(doctorMapper.dtoToDoctor(doctor));
    }

    @Override
    @Transactional
    public void update(DoctorDTO doctor){
        if(doctorRepository.existsById(doctor.getId())){
            logger.log(Level.INFO, "Update doctor");
            doctorRepository.save(doctorMapper.dtoToDoctor(doctor));
        }
    }

    @Override
    @Transactional
    public void delete(Long id){
        logger.log(Level.INFO, "Remove doctor");
//        List<PatientDTO> patientDTOS = patientRepository.getPatientsByDoctorId(id);
//        for(PatientDTO patientDTO: patientDTOS){
//            patientRepository.delete(patientDTO);
//        }
        doctorRepository.delete(doctorRepository.getById(id));
    }
}
