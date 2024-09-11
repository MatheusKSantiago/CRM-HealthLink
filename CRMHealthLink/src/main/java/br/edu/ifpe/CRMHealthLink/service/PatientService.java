package br.edu.ifpe.CRMHealthLink.service;

import br.edu.ifpe.CRMHealthLink.service.dto.mapper.PatientMapper;
import br.edu.ifpe.CRMHealthLink.service.dto.patientDto.PatientCreateDto;
import br.edu.ifpe.CRMHealthLink.domain.entity.Patient;
import br.edu.ifpe.CRMHealthLink.exception.ResourceNotFoundException;
import br.edu.ifpe.CRMHealthLink.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public Patient save(PatientCreateDto patientDTO) {
        Patient patient = PatientMapper.toPatient(patientDTO);
        patient.setPassword(encoder.encode(patient.getPassword()));
        return patientRepository.save(patient);
    }

    @Transactional(readOnly = true)
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encomtrado"));
    }

    @Transactional
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id,PatientCreateDto patientCreateDto) {
        Patient patient = findById(id);

        patient.setName(patientCreateDto.getName());
        patient.setBirthDate(patientCreateDto.getBirthDate());
        patient.setEmail(patientCreateDto.getEmail());
        patient.setCpf(patientCreateDto.getCpf());

        patient.setPassword(patientCreateDto.getPassword());

        patientRepository.save(patient);

    }


}