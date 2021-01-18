package com.restapi.beans.model.segmentation.age;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.beans.model.ad.Ad;
import com.restapi.beans.model.segmentation.Segmentation;
import com.restapi.beans.model.user.User;

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
@Table(name="AGES")
public class Age implements Segmentation{


	@Id
	@Column(name = "ID_AGE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAge; 
	
	@Builder.Default
	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;
	
	@Column(name = "AGE", nullable = false, length = 10)
	private int age;

	@ManyToMany(mappedBy = "ages")
	@JsonIgnore
	private List<Ad> ads;

	@JsonIgnore
	@OneToMany(mappedBy = "age", fetch = FetchType.LAZY)
	private List<User> users;


}
