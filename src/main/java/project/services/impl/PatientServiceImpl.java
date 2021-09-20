package project.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.MedicineDTO;
import project.dto.PatientDTO;
import project.entity.Medicine;
import project.entity.Patient;
import project.mappers.DoctorMapper;
import project.mappers.PatientInfoMapper;
import project.mappers.PatientMapper;
import project.repositories.PatientRepository;
import project.services.DoctorService;
import project.services.MedicineService;
import project.services.PatientInfoService;
import project.services.PatientService;

import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorService doctorService;
    private final PatientInfoService patientInfoService;
    private final MedicineService medicineService;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final PatientInfoMapper patientInfoMapper;
    private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, DoctorService doctorService,
                              PatientInfoService patientInfoService, PatientMapper patientMapper, DoctorMapper doctorMapper,
                              PatientInfoMapper patientInfoMapper, MedicineService medicineService){
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
        this.patientInfoService = patientInfoService;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.patientInfoMapper = patientInfoMapper;
        this.medicineService = medicineService;
    }

    @Override
    @Transactional
    public List<PatientDTO> getPatients(){
        logger.log(Level.INFO, "Get patients");
        return patientMapper.patientsToDTO(patientRepository.findAll());
    }

    @Override
    @Transactional
    public PatientDTO getPatientById(Long id){
        logger.log(Level.INFO, "Get patient by id");
        return patientMapper.patientToDTO(patientRepository.getById(id));
    }

    @Override
    @Transactional
    public PatientDTO create(PatientDTO patient) {
        logger.log(Level.INFO, "Create patient");
        Patient patientEntity = patientMapper.dtoToPatient(patient);
        if(patientEntity.getDoctor().getId() != null){
            patientEntity.setDoctor(doctorMapper.dtoToDoctor(doctorService.getDoctorById(patient.getDoctor().getId())));
        } else {
            patientEntity.setDoctor(doctorService.create(doctorMapper.doctorToDTO(patientEntity.getDoctor())));
        }
        patientEntity.setPatientInfo(patientInfoMapper.dtoToPatientInfo(patientInfoService.create(patientInfoMapper.patientInfoToDTO(patientEntity.getPatientInfo()))));
        List<Medicine> medicines = new ArrayList<>();
        for(MedicineDTO medicineDTO: patient.getMedicine()){
            if(medicineDTO.getId() != null) {
                medicines.add(medicineService.getMedicineById(medicineDTO.getId()));
            } else medicines.add(medicineService.create(medicineDTO));
        }
        patientEntity.setMedicine(medicines);
        return patientMapper.patientToDTO(patientRepository.save(patientEntity));
    }

    @Override
    @Transactional
    public boolean update(PatientDTO patient) {
        logger.log(Level.INFO, "Update patient");
        if(patientRepository.existsById(patient.getId())){
            patientMapper.patientToDTO(patientRepository.save(patientMapper.dtoToPatient(patient)));
            return true;
        } return false;
    }

    @Override
    @Transactional
    public boolean delete(PatientDTO patient) {
        logger.log(Level.INFO, "Remove patient");
        patientRepository.delete(patientMapper.dtoToPatient(patient));
        return true;
    }

}
