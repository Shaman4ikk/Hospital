package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dto.MedicineDTO;
import project.services.MedicineService;

import java.util.List;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService){
        this.medicineService = medicineService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineDTO>> getMedicine(){
        return new ResponseEntity<>(medicineService.getMedicine(), HttpStatus.OK);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable Long id){
        return new ResponseEntity<>(medicineService.getMedicineByIdDTO(id), HttpStatus.OK );
    }
    @PostMapping
    public ResponseEntity<MedicineDTO> create(@RequestBody MedicineDTO medicine){
        medicineService.create(medicine);
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }

    @PutMapping
    public void update(@RequestBody MedicineDTO medicine){
        medicineService.update(medicine);
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            medicineService.delete(id);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
