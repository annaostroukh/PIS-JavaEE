package fit.pis.crm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="car")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.REFRESH,CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name = "brand")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Brand brand;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "model")
	private CarModel model;
	
	@NotNull(message = "Year cannot be empty")
	@Column(name = "car_year")
	private String year;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "equipment")
	private String equipment;
	
	@NotNull(message = "Price cannot be empty")
	@Column(name = "price")
	private Double price;
	
	@ManyToMany(targetEntity=Client.class, fetch = FetchType.EAGER, mappedBy="cars", 
			cascade=CascadeType.ALL)
	private Set<Client> clients = new HashSet<Client>();
	
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
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public CarModel getModel() {
		return model;
	}
	public void setModel(CarModel model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
