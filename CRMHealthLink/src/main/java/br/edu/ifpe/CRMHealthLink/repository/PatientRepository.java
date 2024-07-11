package br.edu.ifpe.CRMHealthLink.repository;

import br.edu.ifpe.CRMHealthLink.entity.Employee;
import br.edu.ifpe.CRMHealthLink.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
