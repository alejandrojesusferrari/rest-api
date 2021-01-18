package com.restapi.service.model.segmentation.gender;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restapi.beans.model.segmentation.gender.Gender;
import com.restapi.repository.model.segmentation.gender.GenderRepository;

@Service
@Transactional
public class GenderService {

	@Autowired
	private GenderRepository genderRepository;

	public List<Gender> findAll() {
		return genderRepository.findAll();
	}

	public Page<Gender> findPaginated(Pageable pgenderable) {
		return genderRepository.findAll(pgenderable);
	}

	public Gender findById(Long id) {
		return genderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Gender not found with id " + id));
	}
	
	public Iterable<Gender> findByPredicate(BooleanBuilder predicate) {
		return genderRepository.findAll(predicate.getValue());
	}
	
	public Page<Gender> findByPredicatePaginated(BooleanBuilder predicate, Pageable pageble) {
		return genderRepository.findAll(predicate.getValue(), pageble);
	}

	public Gender create(Gender gender) {
		return genderRepository.save(gender);
	}

	public Gender update(Gender gender) {
		return genderRepository.findById(gender.getIdGender())
				.map(prevGender -> {
					return genderRepository.save(gender);
				}).orElseThrow(() -> new RuntimeException("Gender not found with id " + gender.getIdGender()));

		}

	public ResponseEntity<Object> delete(Long id) {
		return genderRepository.findById(id)
				.map(entity -> {
					genderRepository.delete(entity);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new RuntimeException("Gender not found with id " + id));
	}

	public List<Gender> findAllActives() {

		BooleanBuilder predicate = BuildPredicateActive();
		return (List<Gender>) genderRepository.findAll(predicate.getValue());
		
	}

	private BooleanBuilder BuildPredicateActive(
			) {
		
	    BooleanBuilder predicate = new BooleanBuilder();
//		QGender qGender = QGender.gender;
//
//		predicate.and(qGender.active.eq("A"));
		
		return predicate;
	}



}
