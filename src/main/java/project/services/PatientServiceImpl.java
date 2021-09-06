package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.PatientDTO;
import project.entity.Patient;
import project.mappers.DoctorMapper;
import project.mappers.PatientInfoMapper;
import project.mappers.PatientMapper;
import project.repositories.PatientRepository;

import java.util.*;

@Service
public class PatientServiceImpl implements project.servicesentity.PatientService {

    private final PatientRepository patientRepository;
    private final DoctorServiceImpl doctorService;
    private final PatientInfoServiceImpl patientInfoService;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final PatientInfoMapper patientInfoMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, DoctorServiceImpl doctorService,
                              PatientInfoServiceImpl patientInfoService, PatientMapper patientMapper, DoctorMapper doctorMapper, PatientInfoMapper patientInfoMapper){
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
        this.patientInfoService = patientInfoService;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.patientInfoMapper = patientInfoMapper;
    }

    @Override
    @Transactional
    public List<PatientDTO> getPatients(){
        return patientMapper.patientsToDTO(patientRepository.findAll());
    }

    @Override
    @Transactional
    public PatientDTO getPatientById(Long id){
        return patientMapper.patientToDTO(patientRepository.getById(id));
    }

    @Override
    @Transactional
    public void create(PatientDTO patient) {
        Patient patientEntity = patientMapper.dtoToPatient(patient);
        if(patientEntity.getDoctor().getId() != null){
            patientEntity.setDoctor(doctorMapper.dtoToDoctor(doctorService.getDoctorById(patient.getDoctor().getId())));
        } else {
            patientEntity.setDoctor(doctorMapper.dtoToDoctor(doctorService.create(doctorMapper.doctorToDTO(patientEntity.getDoctor()))));
        }

        patientRepository.save(patientEntity);
        patientEntity.getPatientInfo().setId(patientEntity.getId());
        patientInfoService.create(patientInfoMapper.patientInfoToDTO(patientEntity.getPatientInfo()));
    }

    @Override
    @Transactional
    public void update(PatientDTO patient) {
        if(patientRepository.existsById(patient.getId())){
            patientRepository.save(patientMapper.dtoToPatient(patient));
        }
    }

    @Override
    @Transactional
    public void delete(PatientDTO patient) {
        patientRepository.delete(patientMapper.dtoToPatient(patient));
    }


}
