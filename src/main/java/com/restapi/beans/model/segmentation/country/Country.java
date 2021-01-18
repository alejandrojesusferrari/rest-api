package com.restapi.beans.model.segmentation.country;

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

import com.restapi.beans.model.segmentation.Segmentation;
import com.restapi.beans.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.beans.model.ad.Ad;

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
@Table(name = "COUNTRYS")
public class Country implements Segmentation {

	@Id
	@Column(name = "ID_COUNTRY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCountry;

	@Builder.Default
	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;

	@Column(name = "COUNTRY", nullable = false, length = 50)
	private String country;

	@JsonIgnore
	@ManyToMany(mappedBy = "countrys")
	private List<Ad> ads;

	@JsonIgnore
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<User> users;


}
