package com.restapi.repository.model.segmentation.gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.restapi.beans.model.segmentation.gender.Gender;


@Repository	
public interface GenderRepository extends JpaRepository<Gender, Long>, QuerydslPredicateExecutor<Gender> {


}
