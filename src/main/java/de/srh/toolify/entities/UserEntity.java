package de.srh.toolify.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", columnDefinition = "int")
	private Long userId;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
    private String lastname;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "hasRole")
    private String hasRole;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "defaultStreetName")
	private String defaultStreetName;
	
	@Column(name = "defaultStreetNumber", columnDefinition = "int")
	private String defaultStreetNumber;
	
	@Column(name = "defaultPincode", columnDefinition = "bigint")
	private Long  defaultPincode;
	
	@Column(name = "defaultCity")
	private String defaultCity;
	
	@Column(name = "createdOn", columnDefinition = "datetime")
	private Instant createdOn;
	
	@Column(name = "updatedOn", columnDefinition = "datetime")
	private Instant updatedOn;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDefaultStreetName() {
		return defaultStreetName;
	}

	public void setDefaultStreetName(String defaultStreetName) {
		this.defaultStreetName = defaultStreetName;
	}

	public String getDefaultStreetNumber() {
		return defaultStreetNumber;
	}

	public void setDefaultStreetNumber(String defaultStreetNumber) {
		this.defaultStreetNumber = defaultStreetNumber;
	}

	public Long getDefaultPincode() {
		return defaultPincode;
	}

	public void setDefaultPincode(Long defaultPincode) {
		this.defaultPincode = defaultPincode;
	}

	public String getDefaultCity() {
		return defaultCity;
	}

	public void setDefaultCity(String defaultCity) {
		this.defaultCity = defaultCity;
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

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", password=" + password + ", hasRole=" + hasRole + ", mobile=" + mobile
				+ ", defaultStreetName=" + defaultStreetName + ", defaultStreetNumber=" + defaultStreetNumber
				+ ", defaultPincode=" + defaultPincode + ", defaultCity=" + defaultCity + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + "]";
	}
}
