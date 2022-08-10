package br.puc.edson.telepsicologiapsicologoservice.repository.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Builder
@Data
public class Patient {

    @Id
    private String cpf;
    private String cpfHash;
    private String email;
    private String emailHash;
    private String name;
    private String password;
}
