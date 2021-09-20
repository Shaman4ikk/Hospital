package project.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.MedicineDTO;
import project.entity.Medicine;
import project.mappers.MedicineMapper;
import project.repositories.MedicineRepository;
import project.services.MedicineService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;
    private final Logger logger = LogManager.getLogger(MedicineServiceImpl.class);

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, MedicineMapper medicineMapper) {
        this.medicineRepository = medicineRepository;
        this.medicineMapper = medicineMapper;
    }

    @Override
    @Transactional
    public List<MedicineDTO> getMedicine() {
        logger.log(Level.INFO, "Start getting medicines");
        return new ArrayList<>(medicineMapper.medicinesToDTO(medicineRepository.findAll()));
    }

    @Override
    @Transactional
    public Medicine getMedicineById(Long id) {
        if (medicineRepository.existsById(id)) {
            logger.log(Level.INFO, "Start getting medicine by id");
            return medicineRepository.getById(id);
        }
        return null;
    }

    @Override
    @Transactional
    public MedicineDTO getMedicineByIdDTO(Long id) {
        if (medicineRepository.existsById(id)) {
            logger.log(Level.INFO, "Start getting medicineDTO by id");
            return medicineMapper.medicineToDTO(getMedicineById(id));
        }
        return null;
    }

    @Override
    @Transactional
    public Medicine create(MedicineDTO medicine) {
        logger.log(Level.INFO, "Start creating new medicine");
        return medicineRepository.save(medicineMapper.dtoToMedicine(medicine));
    }

    @Override
    @Transactional
    public void update(MedicineDTO medicine) {
        if (medicineRepository.existsById(medicine.getId())) {
            logger.log(Level.INFO, "Update medicine");
            medicineRepository.save(medicineMapper.dtoToMedicine(medicine));
        }
    }

    @Override
    @Transactional
    public void delete(MedicineDTO medicine) {
        logger.log(Level.INFO, "Remove medicine");
        medicineRepository.delete(medicineMapper.dtoToMedicine(medicine));
    }

}
