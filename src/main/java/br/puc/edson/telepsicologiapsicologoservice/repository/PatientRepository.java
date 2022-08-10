package br.puc.edson.telepsicologiapsicologoservice.repository;

import br.puc.edson.telepsicologiapsicologoservice.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    boolean existsByCpfHash(String cpfHash);

    Optional<Patient> findByEmailHash(String emailHash);

}
