package services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import project.repositories.MedicineRepository;
import project.services.MedicineService;

public class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;


    @InjectMocks
    private final MedicineService medicineService;

    public MedicineServiceTest(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
}
