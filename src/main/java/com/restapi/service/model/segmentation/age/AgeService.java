package com.restapi.service.model.segmentation.age;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restapi.beans.model.segmentation.age.Age;
import com.restapi.repository.model.segmentation.age.AgeRepository;

@Service
@Transactional
public class AgeService {

	@Autowired
	private AgeRepository ageRepository;

	public List<Age> findAll() {
		return ageRepository.findAll();
	}

	public Page<Age> findPaginated(Pageable pageable) {
		return ageRepository.findAll(pageable);
	}

	public Age findById(Long id) {
		return ageRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Age not found with id " + id));
	}
	
	public Iterable<Age> findByPredicate(BooleanBuilder predicate) {
		return ageRepository.findAll(predicate.getValue());
	}
	
	public Page<Age> findByPredicatePaginated(BooleanBuilder predicate, Pageable pageable) {
		return ageRepository.findAll(predicate.getValue(), pageable);
	}

	public Age create(Age age) {
		return ageRepository.save(age);
	}

	public Age update(Age age) {
		return ageRepository.findById(age.getIdAge())
				.map(prevAge -> {
					return ageRepository.save(age);
				}).orElseThrow(() -> new RuntimeException("Age not found with id " + age.getIdAge()));

		}

	public ResponseEntity<Object> delete(Long id) {
		return ageRepository.findById(id)
				.map(entity -> {
					ageRepository.delete(entity);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new RuntimeException("Age not found with id " + id));
	}

	public List<Age> findAllActives() {

		BooleanBuilder predicate = BuildPredicateActive();
		return (List<Age>) ageRepository.findAll(predicate.getValue());
		
	}

	private BooleanBuilder BuildPredicateActive(
			) {
		
	    BooleanBuilder predicate = new BooleanBuilder();
//		QAge qAge = QAge.age;
//
//		predicate.and(qAge.active.eq("A"));
		
		return predicate;
	}
}
