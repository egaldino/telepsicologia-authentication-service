package br.puc.edson.telepsicologiapsicologoservice.repository.patient;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    boolean existsByCpfHash(String cpfHash);

    Optional<Patient> findByEmailHash(String emailHash);

}
