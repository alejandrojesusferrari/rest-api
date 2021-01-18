package com.restapi.web.controller.adCreatorController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.beans.model.ad.Ad;
import com.restapi.service.catalog.adCreator.AdCreatorService;
import com.restapi.service.util.ExceptionManagerService;

@RestController
@RequestMapping("/ad")
public class AdCreatorController {
	
	
	@Autowired
	private ExceptionManagerService exceptionManagerService;

	@Autowired
	private AdCreatorService adCreatorService;

	
	@RequestMapping(value = "/create-ad",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  createAd(
			@RequestBody Ad ad) throws Exception 
	{
		try {
			
			Ad adCreated = adCreatorService.createAd(ad);
			
			return ResponseEntity.created(null)
					.header("Some header", "Some heder value")
					.body(adCreated);

		} catch (Exception e) {
			ResponseEntity<String> responseEntity = exceptionManagerService.manageGenericException(e);
			return responseEntity;
		}	
	
	
	}
	
	
	
	
	

}
