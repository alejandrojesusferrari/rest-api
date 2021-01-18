package com.restapi.service.catalog.adPublicator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.beans.dto.user.UserDTO;
import com.restapi.beans.model.ad.Ad;
import com.restapi.service.catalog.userDTO.UserDTOService;
import com.restapi.service.model.ad.AdService;





@Service
public class AdPublicatorService {

	@Autowired
	private AdService adService;  
	
	@Autowired
	private UserDTOService userDTOService;

	

	public List<Ad> generateAdPublication(Long userId) {
		
		UserDTO userDTO = null; 
		
		if (userId!=null) {
			userDTO = userDTOService.getUserInfo(userId);
		}


		List<Ad> ads = null;  
		if (userDTO != null) {
			/*
			 * Si tengo usuario, quiere decir que puedo segmentar. 
			 */
			ads = adService.findAllSegmentedActives(userDTO.getUser());

		} else {
			/*
			 * En caso de no tener usuario, quiere decir que no tengo informacion para segmentar y busco todos. 
			 */
			ads = adService.findAllActives();

		}
		
		return randomizeAds(ads);

	}

	
	private List<Ad> randomizeAds(List<Ad> allAds) {
		
		/*
		 * IMPORTANTE: Por una cuestion de tiempo utilizaremos una solucion de lo menos performante que se puede imaginar
		 * pero capaz de responder a las nececidades del negocio
		 */
		
		
		/*
		 * Generaremos una lista la cual va a tener los ids de adds los cuales tomaremos para sortear. 
		 * Estos ids, se repetirar tantas veces como precio tenga, de manera tal de que a mas precio, mas repeticiones, y mas posibilidades
		 */
        ArrayList<Long> idsToRamdom = new ArrayList<Long>();
		
		allAds.stream().forEach((p)-> {
			/*
			 * Verifico si el ad es apto para participar del sorteo mas alla de la segmentacion. 
			 */
			if (checkConditions(p)) {
				Long priceRepeat = p.getPrice().longValue();
				for (int i = 0; i < priceRepeat; i++) {
					idsToRamdom.add(p.getIdAd());
				}
			} 
			
		});


		List<Ad> selectedAds = new ArrayList<Ad>();
		
		
		//aca podriamos parametrizar el 3, pero para simplificar el ejercicio lo harcodearemos
		while ((selectedAds.size()<3) && (!allAds.isEmpty())) {
			
			Random r = new Random();
			Long e = idsToRamdom.get(r.nextInt(idsToRamdom.size()));
		
			try {
				Optional<Ad> op = allAds.stream().
					       filter(p -> p.getIdAd().equals(e)).
					       findFirst();
				
						Ad currentAd = op.get();
						selectedAds.add(currentAd);

						allAds.remove(currentAd);
						
						currentAd.discountPrice();
						
						adService.update(currentAd);
						
						
			} catch (NoSuchElementException e2) {
				// do nothing 
			}

		
		}
		
		

			
		return selectedAds;
		
	}


	private boolean checkConditions(Ad ad) {

		
		
		/*
		 * Aqui iremos paso a paso para explicar la logica usada
		 */
		BigDecimal remainingAmount = ad.getTotalAmount().subtract(ad.getConsumedAmount()); 
		BigDecimal currentPrice = ad.getPrice();
		BigDecimal remaningDays = new BigDecimal((ad.getEndDate().getTime() - new Date().getTime())/86400000);
		if (remaningDays.compareTo(new BigDecimal(0))>1) {
			return false;
		}
		BigDecimal remainingAverage = remainingAmount.divide(remaningDays, 2, RoundingMode.HALF_UP);
		
		/*
		 * Comparamos el resultado del saldo promedio restante con el precio, si es mayor quiere decir que al menos
		 * puede llegar con un anuncio por dia, en ese caso, puede participar del sorteo. 
		 * de esa manera aseguramos que el ultimo dia va a quedar con saldo para tener un anuncio el ultimo dia y 
		 * ademas, vamos a tener una distribucion mas equitativa.
		 */
		
		if ((remainingAverage.compareTo(currentPrice))==(1)) {
			return true;
		}
			
		return false;
	}
	
	
	







}
