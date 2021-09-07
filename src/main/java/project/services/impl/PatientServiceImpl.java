package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.MedicineDTO;
import project.dto.PatientDTO;
import project.entity.Medicine;
import project.entity.Patient;
import project.mappers.DoctorMapper;
import project.mappers.MedicineMapper;
import project.mappers.PatientInfoMapper;
import project.mappers.PatientMapper;
import project.repositories.PatientRepository;
import project.services.PatientService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorServiceImpl doctorService;
    private final PatientInfoServiceImpl patientInfoService;
    private final MedicineServiceImpl medicineService;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final PatientInfoMapper patientInfoMapper;
    private final MedicineMapper medicineMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, DoctorServiceImpl doctorService,
                              PatientInfoServiceImpl patientInfoService, PatientMapper patientMapper, DoctorMapper doctorMapper,
                              PatientInfoMapper patientInfoMapper, MedicineMapper medicineMapper, MedicineServiceImpl medicineService){
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
        this.patientInfoService = patientInfoService;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.patientInfoMapper = patientInfoMapper;
        this.medicineMapper = medicineMapper;
        this.medicineService = medicineService;
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
        patientRepository.save(patientEntity);
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
