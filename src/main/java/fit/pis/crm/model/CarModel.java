package fit.pis.crm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="model")
public class CarModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "model_id")
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name="model_name")
	private String modelName;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand", nullable = false)
	private Brand brand;
	
	@OneToMany(targetEntity=Car.class,fetch = FetchType.EAGER, mappedBy = "model", 
			cascade={CascadeType.REFRESH,CascadeType.MERGE}, orphanRemoval = true)
	private Set<Car> cars = new HashSet<Car>();

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

}
