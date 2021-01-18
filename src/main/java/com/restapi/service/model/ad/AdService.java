package com.restapi.service.model.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restapi.beans.model.ad.Ad;
import com.restapi.beans.model.ad.QAd;
import com.restapi.beans.model.user.User;
import com.restapi.repository.model.ad.AdRepository;

@Service
@Transactional
public class AdService {

	@Autowired
	private AdRepository adRepository;

	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	public Page<Ad> findPaginated(Pageable pageable) {
		return adRepository.findAll(pageable);
	}

	public Ad findById(Long id) {
		return adRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ad not found with id " + id));
	}
	
	public Iterable<Ad> findByPredicate(BooleanBuilder predicate) {
		return adRepository.findAll(predicate.getValue());
	}
	
	public Page<Ad> findByPredicatePaginated(BooleanBuilder predicate, Pageable pageable) {
		return adRepository.findAll(predicate.getValue(), pageable);
	}

	public Ad create(Ad ad) {
		return adRepository.save(ad);
	}

	public Ad update(Ad ad) {
		return adRepository.findById(ad.getIdAd())
				.map(prevAd -> {
					return adRepository.save(ad);
				}).orElseThrow(() -> new RuntimeException("Ad not found with id " + ad.getIdAd()));

		}

	public ResponseEntity<Object> delete(Long id) {
		return adRepository.findById(id)
				.map(entity -> {
					adRepository.delete(entity);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new RuntimeException("Ad not found with id " + id));
	}

	public List<Ad> findAllActives() {

		BooleanBuilder predicate = BuildPredicateActive();
		return (List<Ad>) adRepository.findAll(predicate.getValue());
		
	}

	private BooleanBuilder BuildPredicateActive(
			) {
		
	    BooleanBuilder predicate = new BooleanBuilder();
		QAd qAd = QAd.ad;

		
		predicate.and(qAd.active.eq(true));
		
		return predicate;
	}

	public List<Ad> findAllSegmentedActives(User user) {
		BooleanBuilder predicate = BuildPredicateSegmentedActive(user);
		return (List<Ad>) adRepository.findAll(predicate.getValue());
	}
	
	private BooleanBuilder BuildPredicateSegmentedActive(User user){
		
	    BooleanBuilder predicate = new BooleanBuilder();
		QAd qAd = QAd.ad;

		predicate.and(qAd.active.eq(true));
		predicate.and((qAd.ages.contains(user.getAge())).or(qAd.ages.isEmpty()));
		predicate.and((qAd.countrys.contains(user.getCountry())).or(qAd.countrys.isEmpty()));
		predicate.and((qAd.genders.contains(user.getGender())).or(qAd.genders.isEmpty()));
		return predicate;
	}

	
	
	
}
