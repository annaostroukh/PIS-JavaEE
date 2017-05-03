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
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="client", uniqueConstraints=@UniqueConstraint(columnNames = "email"))
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
	private Long id;
	
	@NotNull(message = "Client name cannot be empty")
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	@Column(name = "client_name")
	private String name;
	
	@NotNull(message = "Client surname cannot be empty")
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	@Column(name = "surname")
	private String surname;
	
	@NotNull(message = "Email address cannot be empty")
	@Column(name = "email")
	private String email;
	
	@NotNull(message = "Phone cannot be empty")
	@Size(min = 9, max = 12)
	@Column(name = "phone_number")
	@Pattern(regexp = "[0-9]*", message = "must contain only digits")
	private String phoneNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
	
	@Column(name = "status")
	private String status;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true,
			mappedBy = "client")
	private List<Meeting> meetings = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="client_car",
		      joinColumns=@JoinColumn(name="client_id", referencedColumnName="client_id"),
		      inverseJoinColumns=@JoinColumn(name="car_id", referencedColumnName="car_id"))
	private Set<Car> cars = new HashSet<Car>();

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="clients", 
			cascade=CascadeType.ALL)
	private Set<UserAcc> managers = new HashSet<UserAcc>();


	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}

	public Set<UserAcc> getManagers() {
		return managers;
	}

	public void setManagers(Set<UserAcc> managers) {
		this.managers = managers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	
}
