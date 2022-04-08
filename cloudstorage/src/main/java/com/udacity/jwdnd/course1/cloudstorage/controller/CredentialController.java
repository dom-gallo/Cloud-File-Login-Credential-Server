package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/credential")
public class CredentialController {
	
	
	private final CredentialService credentialService;
	private final UserService userService;
	
	public CredentialController(CredentialService credentialService, UserService userService) {
		this.credentialService = credentialService;
		this.userService = userService;
	}
	
	@PostMapping()
	public String createCredential(Credential credential, Authentication auth){
		int userId = userService.getUser(auth.getName()).getUserId();
		credential.setUserId(userId);
		
		int credentialId = credentialService.createCredential(credential);
		
		return "redirect:/home";
	}
	
	@GetMapping
	public List<Credential> getCredentials(Authentication auth){
		int userId = userService.getUser(auth.getName()).getUserId();
		return credentialService.getCredentialsForUserId(userId);
	}
}
