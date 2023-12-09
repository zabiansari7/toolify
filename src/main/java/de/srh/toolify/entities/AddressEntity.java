package de.srh.toolify.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="addresses")
public class AddressEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
	@Column(name = "addressID", columnDefinition = "int")
	private Long addressID;
	
	@Column(name = "streetName")
	private String streetName;
	
	@Column(name = "streetNumber")
	private int streetNumber;
	
	@Column(name = "cityName")
	private String cityName;
	
	@Column(name = "postCode")
	private int postCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
	private UserEntity user;
	
	@Column(name = "createdOn", columnDefinition = "datetime")
	private Instant createdOn;
	
	@Column(name = "updatedOn", columnDefinition = "datetime")
	private Instant updatedOn;

	public Long getAddressID() {
		return addressID;
	}

	public void setAddressID(Long addressID) {
		this.addressID = addressID;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public Instant getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

}
