package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
