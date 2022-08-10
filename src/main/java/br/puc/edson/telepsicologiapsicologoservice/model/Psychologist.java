package br.puc.edson.telepsicologiapsicologoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Builder
@Data
public class Psychologist {

    @Id
    private String crp;
    private String email;
    private String name;
    private String password;
    private String emailHash;
    private String crpHash;

}
