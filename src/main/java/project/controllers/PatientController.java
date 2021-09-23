package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dto.PatientDTO;
import project.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatients(){
        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patient){
        return new ResponseEntity<>(patientService.create(patient), HttpStatus.OK);
    }

    @PutMapping
    public void update(@RequestBody PatientDTO patient){
        patientService.update(patient);
    }

    @DeleteMapping("id={id}")
    public void delete(@PathVariable Long id){
        patientService.delete(id);
    }
}
