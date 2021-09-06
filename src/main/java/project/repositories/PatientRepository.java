package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
