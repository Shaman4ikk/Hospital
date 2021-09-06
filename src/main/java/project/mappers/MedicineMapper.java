package project.mappers;

import org.mapstruct.Mapper;
import project.dto.MedicineDTO;
import project.entity.Medicine;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    MedicineDTO medicineToDTO(Medicine medicine);
    Medicine dtoToMedicine(MedicineDTO medicineDTO);
    List<MedicineDTO> medicinesToDTO(List<Medicine> medicineList);
}
