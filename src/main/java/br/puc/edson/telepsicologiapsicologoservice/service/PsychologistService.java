package br.puc.edson.telepsicologiapsicologoservice.service;

import br.puc.edson.telepsicologiapsicologoservice.model.Psychologist;
import br.puc.edson.telepsicologiapsicologoservice.repository.PsychologistRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PsychologistService {

    private final PsychologistRepository repository;

    private final DataCryptoService dataCryptoService;


    public Optional<Psychologist> authenticate(String email, String password) {
        return repository.findByEmailHash(DigestUtils.sha256Hex(email))
                .map(this::decrypt)
                .filter(psychologist -> psychologist.getPassword().equals(password));
    }



    public Psychologist register(Psychologist psychologist) {
        Psychologist encryptedRegisterCandidate = encrypt(psychologist);
        if(repository.existsByCrp(encryptedRegisterCandidate.getCrp())){
            throw new RuntimeException("Crp já cadastrado");
        }

        repository.save(encryptedRegisterCandidate);
        return psychologist;
    }

    private Psychologist encrypt(Psychologist psychologist) {
        return Psychologist
                .builder()
                .emailHash(DigestUtils.sha256Hex(psychologist.getEmail()))
                .crpHash(DigestUtils.sha256Hex(psychologist.getCrp()))
                .crp(dataCryptoService.encrypt(psychologist.getCrp()))
                .email(dataCryptoService.encrypt(psychologist.getEmail()))
                .name(dataCryptoService.encrypt(psychologist.getName()))
                .password(dataCryptoService.encrypt(psychologist.getPassword()))
                .build();
    }

    private Psychologist decrypt(Psychologist psychologist) {

        return Psychologist
                .builder()
                .emailHash(psychologist.getEmailHash())
                .crpHash(psychologist.getCrpHash())
                .crp(dataCryptoService.decrypt(psychologist.getCrp()))
                .email(dataCryptoService.decrypt(psychologist.getEmail()))
                .name(dataCryptoService.decrypt(psychologist.getName()))
                .password(dataCryptoService.decrypt(psychologist.getPassword()))
                .build();
    }

}
