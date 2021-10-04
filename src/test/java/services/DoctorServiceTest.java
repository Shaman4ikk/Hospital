package services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.dto.DoctorDTO;
import project.entity.Doctor;
import project.mappers.DoctorMapper;
import project.repositories.DoctorRepository;
import project.services.impl.DoctorServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @DisplayName("Test getting doctors")
    @Test
    public void checkGetDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());
        doctors.add(new Doctor());
        doctors.add(new Doctor());
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        doctorDTOS.add(new DoctorDTO());
        doctorDTOS.add(new DoctorDTO());
        doctorDTOS.add(new DoctorDTO());
        Mockito.when(doctorRepository.findAll()).thenReturn(doctors);
        Mockito.when(doctorMapper.doctorsToDTO(doctors)).thenReturn(doctorDTOS);
        assertEquals(3, doctorService.getDoctors().size());
    }

    @DisplayName("Test getting doctor by Id")
    @Test
    public void getById(){
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(1L);
        Mockito.when(doctorRepository.getById(1L)).thenReturn(doctor);
        Mockito.when(doctorMapper.doctorToDTO(doctor)).thenReturn(doctorDTO);
        assertNotNull(doctorService.getDoctorById(1L).toString());
    }
}
