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
	
	@Column(name = "isAdmin", columnDefinition = "bit")
    private Boolean isAdmin;
	
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
	
	@Column(name = "updateOn", columnDefinition = "datetime")
	private Instant updateOn;
	
	@Column(name = "deletedOn", columnDefinition = "datetime")
	private Instant deletedOn;
	
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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
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
		return updateOn;
	}

	public void setUpdatedOn(Instant updateOn) {
		this.updateOn = updateOn;
	}

	public Instant getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Instant deletedOn) {
		this.deletedOn = deletedOn;
	}
    
}
