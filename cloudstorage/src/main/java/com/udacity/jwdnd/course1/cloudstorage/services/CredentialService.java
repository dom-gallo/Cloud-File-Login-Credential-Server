package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getMockCredentials() {
        List<Credential> credentials = List.of(
                new Credential(1,"github.com", "dewclaw",
                        "encryptionkey",
                        "encryptedPassword", 1),
                new Credential(1,"facebook.com", "dgallo1122",
                        "encryptionkey",
                        "encryptedPassword", 1)
        );
        return credentials;
    }
}
