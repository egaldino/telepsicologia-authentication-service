package br.puc.edson.telepsicologiapsicologoservice.repository.psichologist;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PsychologistRepository extends MongoRepository<Psychologist, String> {

    boolean existsByCrpHash(String crpHash);
    Optional<Psychologist> findByEmailHash(String email);

}
