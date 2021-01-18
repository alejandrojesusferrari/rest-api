package com.restapi.beans.model.segmentation.gender;

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
@Table(name = "GENDERS")
public class Gender implements Segmentation {

	@Id
	@Column(name = "ID_GENDER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGender;

	@Builder.Default
	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;

	@Column(name = "GENDER", nullable = false, length = 50)
	private String gender;

	@JsonIgnore
	@ManyToMany(mappedBy = "genders")
	private List<Ad> ads;

	@JsonIgnore
	@OneToMany(mappedBy = "gender", fetch = FetchType.LAZY)
	private List<User> users;
}
