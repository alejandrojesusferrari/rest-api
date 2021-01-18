package com.restapi.beans.model.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.restapi.beans.model.segmentation.age.Age;
import com.restapi.beans.model.segmentation.country.Country;
import com.restapi.beans.model.segmentation.gender.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok anotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//JPA anotations
@Entity
@Table(name="USERS")
public class User {

	@Id
	@Column(name = "ID_USER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser; 
	
	@Builder.Default
	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;
	
	@Column(name = "USER_NAME", nullable = false, length = 50)
	private String userName;
	
	@Column(name = "USER_PWD", nullable = false, length = 30)
	private String userPWD;

	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_AGE", insertable=true,  updatable=true)
	private Age age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_COUNTRY", insertable=true,  updatable=true)
	private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_GENDER", insertable=true,  updatable=true)
	private Gender gender;
	
	
}
