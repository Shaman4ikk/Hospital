package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dto.DoctorDTO;
import project.servicesentity.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class    DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("")
    public ResponseEntity<List<DoctorDTO>> getDoctors(){
        return new ResponseEntity<>(doctorService.getDoctors(), HttpStatus.OK);
    }

    @GetMapping("id={id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id){
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DoctorDTO> create(@RequestBody DoctorDTO doctor){
        doctorService.create(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PutMapping("")
    public void update(@RequestBody DoctorDTO doctor){
        doctorService.update(doctor);
    }

    @DeleteMapping("")
    public void delete(@RequestBody DoctorDTO doctor){
        doctorService.delete(doctor);
    }

}
