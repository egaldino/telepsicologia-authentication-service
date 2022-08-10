package br.puc.edson.telepsicologiapsicologoservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenValidationResponseDto {

    private Boolean valid;
}
