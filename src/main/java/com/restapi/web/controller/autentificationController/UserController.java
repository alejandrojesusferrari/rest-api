package com.restapi.web.controller.autentificationController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.beans.dto.user.UserDTO;
import com.restapi.beans.model.user.User;
import com.restapi.service.model.user.UserService;
import com.restapi.service.util.ExceptionManagerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ExceptionManagerService exceptionManagerService;

	
	@RequestMapping(value = "/signin",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signin(
			@RequestBody User user) throws Exception{

		
		try {
			
			userService.create(user);

			
			return ResponseEntity.created(null)
					.body(user);

		} catch (Exception e) {

			ResponseEntity<String> responseEntity = exceptionManagerService.manageGenericException(e);
			return responseEntity;

		}	

	}

	
	
	
	@RequestMapping(value = "/login",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(
			@RequestParam("user") String username, 
			@RequestParam("password") String pwd,
			@RequestHeader(name = "UserId", required = false) Long userId) throws Exception {
		
		try {
			
			//a modo de simplificar hacemos algo muy simple para simular un logueo
			
			User user = userService.findById(userId);
			String token = getJWTToken(username);
			UserDTO userDTO = new UserDTO();
			userDTO.setUser(user);
			userDTO.setToken(token);	
			
			return ResponseEntity.ok()
					.body(userDTO);

		} catch (Exception e) {

			ResponseEntity<String> responseEntity = exceptionManagerService.manageGenericException(e);
			return responseEntity;

		}	


		
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
