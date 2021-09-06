package project.servicesentity;

import project.dto.MedicineDTO;

import java.util.List;

public interface MedicineService {

    MedicineDTO getMedicineById(Long id);
    List<MedicineDTO> getMedicine();
    void create(MedicineDTO medicine);
    void update(MedicineDTO medicine);
    void delete(MedicineDTO medicine);
}
