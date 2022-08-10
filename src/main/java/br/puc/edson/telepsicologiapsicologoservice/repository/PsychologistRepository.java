package br.puc.edson.telepsicologiapsicologoservice.repository;

import br.puc.edson.telepsicologiapsicologoservice.model.Psychologist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PsychologistRepository extends MongoRepository<Psychologist, String> {

    boolean existsByCrp(String crp);
    Optional<Psychologist> findByEmailHash(String email);

}
