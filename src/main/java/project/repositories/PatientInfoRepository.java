package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.PatientInfo;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {
}
