package br.puc.edson.telepsicologiapsicologoservice.api;


import br.puc.edson.telepsicologiapsicologoservice.dto.*;
import br.puc.edson.telepsicologiapsicologoservice.mapper.PatientMapper;
import br.puc.edson.telepsicologiapsicologoservice.mapper.PsychologistMapper;
import br.puc.edson.telepsicologiapsicologoservice.service.JwtTokenService;
import br.puc.edson.telepsicologiapsicologoservice.service.PatientService;
import br.puc.edson.telepsicologiapsicologoservice.service.PsychologistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AuthenticationController {

    private final PsychologistService psychologistService;

    private final PatientService patientService;
    
    private final JwtTokenService jwtTokenService;
    

    @PostMapping("/psychologist/register")
    ResponseEntity<LoginResponseDto> registerPsychologist(@RequestBody PsychologistRegisterFormDto form){

       return  Optional.of(form)
                .map(PsychologistMapper.INSTANCE::dtoToModel)
                .map(psychologistService::register)
               .map(user -> LoginResponseDto
                       .builder()
                       .userId(user.getCrp())
                       .token(jwtTokenService.generateToken(user.getCrp()))
                       .build())
                .map(ResponseEntity::ok)
               .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/patient/register")
    ResponseEntity<LoginResponseDto> registerPatient(@RequestBody PatientRegisterFormDto form){

        return  Optional.of(form)
                .map(PatientMapper.INSTANCE::dtoToModel)
                .map(patientService::register)
                .map(user -> LoginResponseDto
                        .builder()
                        .userId(user.getCpf())
                        .token(jwtTokenService.generateToken(user.getCpf()))
                        .build())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/psychologist/login")
    ResponseEntity<LoginResponseDto> loginPsychologist(@RequestBody LoginDto form){
        return psychologistService.authenticate(form.getEmail(), form.getPassword())
                .map(user -> LoginResponseDto
                        .builder()
                        .userId(user.getCrp())
                        .token(jwtTokenService.generateToken(user.getCrp()))
                        .build())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/patient/login")
    ResponseEntity<LoginResponseDto> loginPatient(@RequestBody LoginDto form){
        return patientService.authenticate(form.getEmail(), form.getPassword())
                .map(user -> LoginResponseDto
                        .builder()
                        .userId(user.getCpf())
                        .token(jwtTokenService.generateToken(user.getCpf()))
                        .build())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/validateToken")
    public TokenValidationResponseDto validateToken(@RequestBody TokenValidationRequestDto tokenValidationRequestDto) {
        return TokenValidationResponseDto.builder().valid(jwtTokenService.validateToken(tokenValidationRequestDto.getToken())).build();
    }
}
