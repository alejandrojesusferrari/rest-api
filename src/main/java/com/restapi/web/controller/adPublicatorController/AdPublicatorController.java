package com.restapi.web.controller.adPublicatorController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.beans.model.ad.Ad;
import com.restapi.service.catalog.adPublicator.AdPublicatorService;
import com.restapi.service.util.ExceptionManagerService;

@RestController
@RequestMapping("/publicator")
public class AdPublicatorController {

	
	
	@Autowired
	private ExceptionManagerService exceptionManagerService;

	@Autowired
	private AdPublicatorService adPublicatorService;
	

	

	@RequestMapping(value = "/publicate-ads",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  get(
			@RequestHeader(name = "UserId", required = false) Long userId) throws Exception 
	{
		
		try {
			
								
			 	List<Ad> ads = adPublicatorService.generateAdPublication(userId);
			 	
				return ResponseEntity.ok()
						.body(ads);

			
		} catch (Exception e) {

			ResponseEntity<String> responseEntity = exceptionManagerService.manageGenericException(e);
			return responseEntity;

		}		 
	}

}
