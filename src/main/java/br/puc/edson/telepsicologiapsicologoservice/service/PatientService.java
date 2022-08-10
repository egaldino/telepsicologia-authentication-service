package br.puc.edson.telepsicologiapsicologoservice.service;

import br.puc.edson.telepsicologiapsicologoservice.model.Patient;
import br.puc.edson.telepsicologiapsicologoservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    private final DataCryptoService dataCryptoService;


    public Optional<Patient> authenticate(String email, String password) {
        return repository.findBySearchHash(DigestUtils.sha256Hex(email))
                .map(this::decrypt)
                .filter(psychologist -> psychologist.getPassword().equals(password));
    }
    public Patient register(Patient patient) {
        Patient encryptedRegisterCandidate = encrypt(patient);
        if(repository.existsByCpf(encryptedRegisterCandidate.getCpf())){
            throw new RuntimeException("Cpf já cadastrado");
        }

        repository.save(encryptedRegisterCandidate);
        return patient;
    }

    private Patient encrypt(Patient patient) {
        return Patient
                .builder()
                .searchHash(DigestUtils.sha256Hex(patient.getEmail()))
                .cpf(dataCryptoService.encrypt(patient.getCpf()))
                .email(dataCryptoService.encrypt(patient.getEmail()))
                .name(dataCryptoService.encrypt(patient.getName()))
                .password(dataCryptoService.encrypt(patient.getPassword()))
                .build();
    }

    private Patient decrypt(Patient patient) {
        return Patient
                .builder()
                .searchHash(patient.getSearchHash())
                .cpf(dataCryptoService.decrypt(patient.getCpf()))
                .email(dataCryptoService.decrypt(patient.getEmail()))
                .name(dataCryptoService.decrypt(patient.getName()))
                .password(dataCryptoService.decrypt(patient.getPassword()))
                .build();
    }
}
