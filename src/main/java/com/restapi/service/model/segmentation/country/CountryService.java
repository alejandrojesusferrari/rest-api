package com.restapi.service.model.segmentation.country;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restapi.beans.model.segmentation.country.Country;
import com.restapi.repository.model.segmentation.country.CountryRepository;

@Service
@Transactional
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	public Page<Country> findPaginated(Pageable pcountryable) {
		return countryRepository.findAll(pcountryable);
	}

	public Country findById(Long id) {
		return countryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Country not found with id " + id));
	}
	
	public Iterable<Country> findByPredicate(BooleanBuilder predicate) {
		return countryRepository.findAll(predicate.getValue());
	}
	
	public Page<Country> findByPredicatePaginated(BooleanBuilder predicate, Pageable pageble) {
		return countryRepository.findAll(predicate.getValue(), pageble);
	}

	public Country create(Country country) {
		return countryRepository.save(country);
	}

	public Country update(Country country) {
		return countryRepository.findById(country.getIdCountry())
				.map(prevCountry -> {
					return countryRepository.save(country);
				}).orElseThrow(() -> new RuntimeException("Country not found with id " + country.getIdCountry()));

		}

	public ResponseEntity<Object> delete(Long id) {
		return countryRepository.findById(id)
				.map(entity -> {
					countryRepository.delete(entity);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new RuntimeException("Country not found with id " + id));
	}

	public List<Country> findAllActives() {

		BooleanBuilder predicate = BuildPredicateActive();
		return (List<Country>) countryRepository.findAll(predicate.getValue());
		
	}

	private BooleanBuilder BuildPredicateActive(
			) {
		
	    BooleanBuilder predicate = new BooleanBuilder();
//		QCountry qCountry = QCountry.country;
//
//		predicate.and(qCountry.active.eq("A"));
		
		return predicate;
	}
}

