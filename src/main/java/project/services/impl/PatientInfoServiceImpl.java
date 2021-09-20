package project.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.PatientInfoDTO;
import project.mappers.PatientInfoMapper;
import project.repositories.PatientInfoRepository;
import project.services.PatientInfoService;

import java.util.List;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    private final PatientInfoRepository patientInfoRepository;
    private final PatientInfoMapper patientInfoMapper;
    private final Logger logger = LogManager.getLogger(PatientInfoServiceImpl.class);

    @Autowired
    public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository, PatientInfoMapper patientInfoMapper){
        this.patientInfoRepository = patientInfoRepository;
        this.patientInfoMapper = patientInfoMapper;
    }

    @Override
    @Transactional
    public List<PatientInfoDTO> getPatientsInfo() {
        logger.log(Level.INFO, "Start getting patientsInfo");
        return patientInfoMapper.patientsInfoToDTO(patientInfoRepository.findAll());
    }

    @Override
    @Transactional
    public PatientInfoDTO getPatientInfoById(Long id){
        logger.log(Level.INFO, "Start getting patientsInfo by id");
        return patientInfoMapper.patientInfoToDTO(patientInfoRepository.getById(id));
    }

    @Override
    @Transactional
    public PatientInfoDTO create(PatientInfoDTO patientInfo){
        logger.log(Level.INFO, "Start creating new patients info");
        return patientInfoMapper.patientInfoToDTO(patientInfoRepository.save(patientInfoMapper.dtoToPatientInfo(patientInfo)));
    }

    @Override
    @Transactional
    public void update(PatientInfoDTO patientInfo){
        if(patientInfoRepository.existsById(patientInfo.getId())){
            logger.log(Level.INFO, "Update patients info");
            patientInfoRepository.save(patientInfoMapper.dtoToPatientInfo(patientInfo));
        }
    }

    @Override
    @Transactional
    public void delete(PatientInfoDTO patientInfo){
        logger.log(Level.INFO, "Remove patients info");
        patientInfoRepository.delete(patientInfoMapper.dtoToPatientInfo(patientInfo));
    }

}
