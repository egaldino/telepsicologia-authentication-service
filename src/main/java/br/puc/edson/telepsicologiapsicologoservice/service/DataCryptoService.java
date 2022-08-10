package br.puc.edson.telepsicologiapsicologoservice.service;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CryptoResult;
import com.amazonaws.encryptionsdk.kms.KmsMasterKey;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataCryptoService {

    private final AwsCrypto awsCrypto;

    private final KmsMasterKeyProvider kmsMasterKeyProvider;

    public String encrypt(String plaintext){
        if(StringUtils.isBlank(plaintext)) return plaintext;


        final Map<String, String> context = Collections.singletonMap("Telemedicina", "Auth");

        final CryptoResult<byte[], KmsMasterKey> encryptResult = awsCrypto.encryptData(kmsMasterKeyProvider, plaintext.getBytes(StandardCharsets.UTF_8), context);
        final byte[] ciphertext = encryptResult.getResult();
        return  Arrays.toString(ciphertext);
    }

    public String decrypt(String ciphertext){
        if(StringUtils.isBlank(ciphertext)) return ciphertext;


        final CryptoResult<byte[], KmsMasterKey> decryptResult = awsCrypto.decryptData(kmsMasterKeyProvider, fromString(ciphertext));

        // The data is correct, so return it.
        return new String(decryptResult.getResult(), StandardCharsets.UTF_8);
    }

    private static byte[] fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        byte[] result = new byte[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Byte.parseByte(strings[i]);
        }
        return result;
    }

}
