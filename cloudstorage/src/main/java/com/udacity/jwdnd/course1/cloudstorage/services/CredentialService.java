package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    
    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
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
    
    public int createCredential(Credential credential){
        // Encrypt password;
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        
        //TODO:
        // Check if this is an existing password being updated by the user.
        
        
        return credentialMapper.createCredential(credential);
    }
    
    public List<Credential> getCredentialsForUserId(int userId){
        return credentialMapper.getCredentialsForUserId(userId);
    }

    public int deleteCredential(int credentialId, int userId){
        return credentialMapper.deleteCredential(credentialId, userId);
    }
}
