package com.restapi.service.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;





@Service
public class ExceptionManagerService {


	

	public ResponseEntity<String> manageGenericException(Exception e) {
		
		/*
		 * Aquí desarrollaríamos un Handler para las excepciones de negocio, 
		 * por una cuestión de tiempos lo dejaremos simplificado a una respuesta 500
		 */

		return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

	}









}
