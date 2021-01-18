package com.restapi.beans.model.ad;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name="ADS")
public class Ad {

	@Id
	@Column(name = "ID_AD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAd; 
	
	@Column(name = "TITLE", nullable = false, length = 50)
	private String title;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 500)
	private String description;
	
	@Column(name = "PRICE", nullable = false, precision=16, scale=2)
	private BigDecimal price;
	
	@Column(name = "CONSUMED_AMOUNT", nullable = false, precision=18, scale=2)
	private BigDecimal consumedAmount;
	
	@Column(name = "TOTAL_AMOUNT", nullable = false, precision=18, scale=2)
	private BigDecimal totalAmount;
	
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	
	@Builder.Default
	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;

	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ADS_AGES",
    joinColumns=@JoinColumn(name = "ID_AD"),
    inverseJoinColumns = @JoinColumn(name = "ID_AGES", 
            updatable=false,insertable=false, nullable = false))
	private List<Age> ages;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ADS_GENDERS",
    joinColumns=@JoinColumn(name = "ID_AD"),
    inverseJoinColumns = @JoinColumn(name = "ID_GENDER", 
            updatable=false,insertable=false, nullable = false))	
	private List<Gender> genders;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ADS_COUNTRYS",
    joinColumns=@JoinColumn(name = "ID_AD"),
    inverseJoinColumns = @JoinColumn(name = "ID_COUNTRY", 
            updatable=false,insertable=false, nullable = false))	
	private List<Country> countrys;

	public void discountPrice() {
		this.consumedAmount = this.consumedAmount.add(this.price);
	}

	
	
	
	
}
