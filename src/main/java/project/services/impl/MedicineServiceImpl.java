package project.services.impl;

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

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, MedicineMapper medicineMapper) {
        this.medicineRepository = medicineRepository;
        this.medicineMapper = medicineMapper;
    }

    @Override
    @Transactional
    public List<MedicineDTO> getMedicine() {
        return new ArrayList<>(medicineMapper.medicinesToDTO(medicineRepository.findAll()));
    }

    @Override
    @Transactional
    public Medicine getMedicineById(Long id) {
        if (medicineRepository.existsById(id)) {
            return medicineRepository.getById(id);
        }
        return null;
    }

    @Override
    public MedicineDTO getMedicineByIdDTO(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Medicine create(MedicineDTO medicine) {
        return medicineRepository.save(medicineMapper.dtoToMedicine(medicine));
    }

    @Override
    @Transactional
    public void update(MedicineDTO medicine) {
        if (medicineRepository.existsById(medicine.getId())) {
            medicineRepository.save(medicineMapper.dtoToMedicine(medicine));
        }
    }

    @Override
    @Transactional
    public void delete(MedicineDTO medicine) {
        medicineRepository.delete(medicineMapper.dtoToMedicine(medicine));
    }


}
