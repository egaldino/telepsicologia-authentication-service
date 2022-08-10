package br.puc.edson.telepsicologiapsicologoservice.config;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsKmsConfig {

    @Value("${aws.kms.keyArn}")
    private String keyArn;

    @Bean
    public AwsCrypto buildAwsCrypto(){
        return AwsCrypto.standard();
    }

    @Bean
    public KmsMasterKeyProvider buildKmsMasterKeyProvider(){
        return KmsMasterKeyProvider.builder()
                .buildStrict(keyArn);
    }

}
