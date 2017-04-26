package fit.pis.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "UserAccount")
@Table(name="useraccount", uniqueConstraints=@UniqueConstraint(columnNames = "email"))
public class UserAccount implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
	private Long id;
	
	@NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	@Column(name = "username", length = 50)
	private String userName;
	
	@NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	@Column(name = "surname", length = 50)
	private String surname;
	
	@NotNull(message = "Email address cannot be empty")
	@Column(name = "email", length = 50)
	private String email;
	
	@NotNull(message = "Start date cannot be empty")
	@DateTimeFormat(pattern="MM/dd/yyyy")
    private Date date;
	
	@NotNull
	@Size(min = 9, max = 12)
	@Column(name = "phone_number", length = 20)
	@Pattern(regexp = "[0-9]*", message = "must contain only digits")
	private String phoneNumber;
	
	@NotNull
    @Size(min = 6, max = 12)
	@Column(name = "password", length = 50)
	private String password;
	
	@Size(min = 6, max = 12)
	@Transient
	private String confirmPassword;
	
	@NotNull
	@Column(name = "enabled")
	private boolean enabled = true;
	
	@NotNull
	@Column(name = "role", length = 20)
	private String role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
