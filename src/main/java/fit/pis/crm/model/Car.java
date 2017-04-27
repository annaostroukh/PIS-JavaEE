package fit.pis.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="car")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
	private Long id;
	
	@NotNull(message = "Brand cannot be empty")
	@Column(name = "brand")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id", nullable = false)
	private Brand brand;
	
	@NotNull(message = "Model cannot be empty")
	@Column(name = "model")
	private String model;
	
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
}
