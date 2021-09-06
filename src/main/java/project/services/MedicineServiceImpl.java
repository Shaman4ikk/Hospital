package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.MedicineDTO;
import project.mappers.MedicineMapper;
import project.repositories.MedicineRepository;
import project.servicesentity.MedicineService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, MedicineMapper medicineMapper){
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
    public MedicineDTO getMedicineById(Long id){
        if(medicineRepository.existsById(id)){
            return medicineMapper.medicineToDTO(medicineRepository.getById(id));
        }
        return null;
    }

    @Override
    @Transactional
    public void create(MedicineDTO medicine) {
        medicineRepository.save(medicineMapper.dtoToMedicine(medicine));
    }

    @Override
    @Transactional
    public void update(MedicineDTO medicine){
        if(medicineRepository.existsById(medicine.getId())){
            medicineRepository.save(medicineMapper.dtoToMedicine(medicine));
        }
    }

    @Override
    @Transactional
    public void delete(MedicineDTO medicine){
        medicineRepository.delete(medicineMapper.dtoToMedicine(medicine));
    }


}
