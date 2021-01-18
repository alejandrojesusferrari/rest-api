package com.restapi.service.catalog.adCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.beans.model.ad.Ad;
import com.restapi.service.model.ad.AdService;





@Service
public class AdCreatorService {

	@Autowired
	private AdService adService;  
	
	
	

	public Ad createAd(Ad ad) {


		return adService.create(ad);

		
	}





}
