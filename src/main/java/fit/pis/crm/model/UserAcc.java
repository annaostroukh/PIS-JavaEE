package fit.pis.crm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="useraccount", uniqueConstraints=@UniqueConstraint(columnNames = "email"))
public class UserAcc implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
	private Long id;
	
	@NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	@Column(name = "username", length = 50)
	private String username;
	
	@NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	@Column(name = "surname", length = 50)
	private String surname;
	
	@NotNull(message = "Email address cannot be empty")
	@Column(name = "email", length = 50)
	private String email;
	
	@NotNull(message = "Start date cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
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
	
	@NotNull(message = "Select a position, please")
	@Column(name = "role", length = 20)
	private String role;
	
	@OneToMany(targetEntity=Meeting.class,fetch = FetchType.EAGER, orphanRemoval = true,
	        mappedBy = "manager")
	private List<Meeting> meetings = new ArrayList<>();
	
	@ManyToMany(targetEntity=Client.class,fetch = FetchType.EAGER, cascade={CascadeType.PERSIST,
		    CascadeType.REFRESH,CascadeType.MERGE})
	@JoinTable(name = "client_manager",
			  joinColumns=@JoinColumn(name="manager_id", referencedColumnName="user_id"),
		      inverseJoinColumns=@JoinColumn(name="client_id", referencedColumnName="client_id"))
	protected Set<Client> clients = new HashSet<Client>();
	
	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
