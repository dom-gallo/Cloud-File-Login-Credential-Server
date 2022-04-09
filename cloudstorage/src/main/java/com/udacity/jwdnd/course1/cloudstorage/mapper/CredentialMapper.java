package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper extends Mapper {
	
	@Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "credentialId")
	public int createCredential(Credential credential);
	
	@Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
	public List<Credential> getCredentialsForUserId(int userId);

	@Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialId} AND userid=#{userId}")
	int deleteCredential(int credentialId, int userId);
}
