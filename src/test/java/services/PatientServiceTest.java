package services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.dto.DoctorDTO;
import project.dto.MedicineDTO;
import project.dto.PatientDTO;
import project.dto.PatientInfoDTO;
import project.entity.Doctor;
import project.entity.Medicine;
import project.entity.Patient;
import project.entity.PatientInfo;
import project.mappers.DoctorMapper;
import project.mappers.MedicineMapper;
import project.mappers.PatientInfoMapper;
import project.mappers.PatientMapper;
import project.repositories.PatientRepository;
import project.services.DoctorService;
import project.services.MedicineService;
import project.services.PatientInfoService;
import project.services.impl.PatientServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientMapper patientMapper;
    @Mock
    private DoctorService doctorService;
    @Mock
    private PatientInfoService patientInfoService;
    @Mock
    private MedicineService medicineService;
    @Mock
    private DoctorMapper doctorMapper;
    @Mock
    private PatientInfoMapper patientInfoMapper;
    @Mock
    private MedicineMapper medicineMapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    public void testGetPatient(){
        Patient patient = new Patient();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        patients.add(new Patient());
        patients.add(new Patient());
        List<PatientDTO> patientDTOS = new ArrayList<>();
        patientDTOS.add(new PatientDTO());
        patientDTOS.add(new PatientDTO());
        patientDTOS.add(new PatientDTO());
        Mockito.when(patientRepository.findAll()).thenReturn(patients);
        Mockito.when(patientMapper.patientsToDTO(patients)).thenReturn(patientDTOS);
        assertEquals( 3,patientService.getPatients().size());
    }

    @Test
    public void getById(){
        Patient patient = new Patient();
        patient.setId(3L);
        Mockito.when(patientRepository.getById(3L)).thenReturn(patient);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(2L);
        Mockito.when(patientMapper.patientToDTO(patient)).thenReturn(patientDTO);
        assertEquals(2L, patientService.getPatientById(3L).getId());
        assertNotNull(patientService.getPatientById(3L));
    }

    @Test
    public void create(){
        List<MedicineDTO> medicineDTOS = new ArrayList<>();
        medicineDTOS.add(new MedicineDTO());

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientInfo(new PatientInfoDTO());
        patientDTO.setDoctor(new DoctorDTO());
        patientDTO.setMedicine(medicineDTOS);
        patientDTO.setId(1L);

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setDoctor(new Doctor());
        patient.setPatientInfo(new PatientInfo());
        patient.setMedicine(new ArrayList<>());

        Mockito.when(doctorService.create(patientDTO.getDoctor())).thenReturn(new Doctor());
        Mockito.when(patientInfoService.create(patientDTO.getPatientInfo())).thenReturn(new PatientInfoDTO());
        Mockito.when(patientRepository.save(patient)).thenReturn(patient);
        Mockito.when(patientMapper.dtoToPatient(patientDTO)).thenReturn(patient);
        Mockito.when(doctorMapper.doctorToDTO(patient.getDoctor())).thenReturn(new DoctorDTO());
        Mockito.when(patientInfoMapper.dtoToPatientInfo(patientDTO.getPatientInfo())).thenReturn(new PatientInfo());
        Mockito.when(patientInfoMapper.patientInfoToDTO(patient.getPatientInfo())).thenReturn(new PatientInfoDTO());
        Mockito.when(patientMapper.patientToDTO(patient)).thenReturn(patientDTO);

        assertNotNull(patientService.create(patientDTO));
    }

    @Test
    public void update() {
        Patient patient = new Patient();
        patient.setDoctor(new Doctor());
        patient.setPatientInfo(new PatientInfo());
        patient.setMedicine(new ArrayList<>());

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setMedicine(new ArrayList<>());
        patientDTO.setDoctor(new DoctorDTO());
        patientDTO.setPatientInfo(new PatientInfoDTO());

        Mockito.when(patientMapper.patientToDTO(patient)).thenReturn(patientDTO);
        Mockito.when(patientMapper.dtoToPatient(patientDTO)).thenReturn(patient);
        Mockito.when(patientRepository.existsById(patientDTO.getId())).thenReturn(true);
        Mockito.when(patientRepository.save(patient)).thenReturn(patient);

        assertTrue(patientService.update(patientDTO));
    }

    @Test
    public void delete(){
        Patient patient = new Patient();
        patient.setDoctor(new Doctor());
        patient.setPatientInfo(new PatientInfo());
        patient.setMedicine(new ArrayList<>());

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setMedicine(new ArrayList<>());
        patientDTO.setDoctor(new DoctorDTO());
        patientDTO.setPatientInfo(new PatientInfoDTO());

        Mockito.when(patientMapper.dtoToPatient(patientDTO)).thenReturn(patient);

        assertTrue(patientService.delete(patientDTO.getId()));
    }
}
