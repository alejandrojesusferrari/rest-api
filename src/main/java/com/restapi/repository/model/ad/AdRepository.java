package com.restapi.repository.model.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.restapi.beans.model.ad.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long>, QuerydslPredicateExecutor<Ad> {

	
}
