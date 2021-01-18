package com.restapi.service.model.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restapi.beans.model.user.User;
import com.restapi.beans.model.user.QUser;
import com.restapi.repository.model.user.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Page<User> findPaginated(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id " + id));
	}
	
	public Iterable<User> findByPredicate(BooleanBuilder predicate) {
		return userRepository.findAll(predicate.getValue());
	}
	
	public Page<User> findByPredicatePaginated(BooleanBuilder predicate, Pageable pageable) {
		return userRepository.findAll(predicate.getValue(), pageable);
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.findById(user.getIdUser())
				.map(prevUser -> {
					return userRepository.save(user);
				}).orElseThrow(() -> new RuntimeException("User not found with id " + user.getIdUser()));

		}

	public ResponseEntity<Object> delete(Long id) {
		return userRepository.findById(id)
				.map(entity -> {
					userRepository.delete(entity);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new RuntimeException("User not found with id " + id));
	}

	public List<User> findAllActives() {

		BooleanBuilder predicate = BuildPredicateActive();
		return (List<User>) userRepository.findAll(predicate.getValue());
		
	}

	private BooleanBuilder BuildPredicateActive(
			) {
		
	    BooleanBuilder predicate = new BooleanBuilder();
		QUser qUser = QUser.user;

		predicate.and(qUser.active.eq(true));
		
		return predicate;
	}
	
	
	
}
