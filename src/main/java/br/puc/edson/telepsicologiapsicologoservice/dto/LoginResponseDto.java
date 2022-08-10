package br.puc.edson.telepsicologiapsicologoservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponseDto {

    private String userId;
    private String token;

}
