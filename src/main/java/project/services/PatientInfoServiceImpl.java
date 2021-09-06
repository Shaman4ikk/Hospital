package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.PatientInfoDTO;
import project.mappers.PatientInfoMapper;
import project.repositories.PatientInfoRepository;

import java.util.List;

@Service
public class PatientInfoServiceImpl implements project.servicesentity.PatientInfoService {

    private final PatientInfoRepository patientInfoRepository;
    private final PatientInfoMapper patientInfoMapper;

    @Autowired
    public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository, PatientInfoMapper patientInfoMapper){
        this.patientInfoRepository = patientInfoRepository;
        this.patientInfoMapper = patientInfoMapper;
    }

    @Override
    @Transactional
    public List<PatientInfoDTO> getPatientsInfo() {
        return patientInfoMapper.patientsInfoToDTO(patientInfoRepository.findAll());
    }

    @Override
    @Transactional
    public PatientInfoDTO getPatientInfoById(Long id){
        return patientInfoMapper.patientInfoToDTO(patientInfoRepository.getById(id));
    }

    @Override
    @Transactional
    public void create(PatientInfoDTO patientInfo){
        patientInfoRepository.save(patientInfoMapper.dtoToPatientInfo(patientInfo));
    }

    @Override
    @Transactional
    public void update(PatientInfoDTO patientInfo){
        if(patientInfoRepository.existsById(patientInfo.getId())){
            patientInfoRepository.save(patientInfoMapper.dtoToPatientInfo(patientInfo));
        }
    }

    @Override
    @Transactional
    public void delete(PatientInfoDTO patientInfo){
        patientInfoRepository.delete(patientInfoMapper.dtoToPatientInfo(patientInfo));
    }

}
