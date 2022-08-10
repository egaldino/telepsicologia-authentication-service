package br.puc.edson.telepsicologiapsicologoservice.mapper;

import br.puc.edson.telepsicologiapsicologoservice.dto.PatientRegisterFormDto;
import br.puc.edson.telepsicologiapsicologoservice.dto.PsychologistRegisterFormDto;
import br.puc.edson.telepsicologiapsicologoservice.model.Patient;
import br.puc.edson.telepsicologiapsicologoservice.model.Psychologist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper( PatientMapper.class );

    Patient dtoToModel(PatientRegisterFormDto psychologist);
}
