package br.puc.edson.telepsicologiapsicologoservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class PatientRegisterFormDto  {

    private String name;
    private String email;
    private String password;
    private String cpf;

}
