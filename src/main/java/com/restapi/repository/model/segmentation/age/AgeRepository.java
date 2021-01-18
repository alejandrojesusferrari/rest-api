package com.restapi.repository.model.segmentation.age;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.restapi.beans.model.segmentation.age.Age;


@Repository	
public interface AgeRepository extends JpaRepository<Age, Long>, QuerydslPredicateExecutor<Age> {


}
