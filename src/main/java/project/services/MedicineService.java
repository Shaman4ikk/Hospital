package project.services;

import project.dto.MedicineDTO;
import project.entity.Medicine;

import java.util.List;

public interface MedicineService {

    Medicine getMedicineById(Long id);
    MedicineDTO getMedicineByIdDTO(Long id);
    List<MedicineDTO> getMedicine();
    Medicine create(MedicineDTO medicine);
    void update(MedicineDTO medicine);
    void delete(MedicineDTO medicine);
}
