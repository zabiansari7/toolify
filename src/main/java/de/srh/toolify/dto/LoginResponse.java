package de.srh.toolify.dto;

public class LoginResponse {
	
	private String firstname;
	private String lastname;
	private String email;
	private String hasRole;
	
	public LoginResponse(String firstname, String lastname, String email, String hasRole) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.hasRole = hasRole;
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
	
	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

}
