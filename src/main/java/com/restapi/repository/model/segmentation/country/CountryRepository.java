package com.restapi.repository.model.segmentation.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.restapi.beans.model.segmentation.country.Country;


@Repository	
public interface CountryRepository extends JpaRepository<Country, Long>, QuerydslPredicateExecutor<Country> {


}
