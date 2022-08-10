package br.puc.edson.telepsicologiapsicologoservice.model;

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
    private String email;
    private String name;
    private String password;
    private String searchHash;

}
