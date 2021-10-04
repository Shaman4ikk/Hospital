package services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import project.repositories.PatientInfoRepository;
import project.services.PatientInfoService;

public class PatientInfoServiceTest {

    @Mock
    private PatientInfoRepository patientInfoRepository;

    @InjectMocks
    private final PatientInfoService patientInfoService;

    public PatientInfoServiceTest(PatientInfoService patientInfoService) {
        this.patientInfoService = patientInfoService;
    }
}
