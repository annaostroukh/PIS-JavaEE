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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="brand")
public class Brand implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "brand_id")
	@GeneratedValue
	private Long id;

	@NotNull(message="Name cannot be empty")
	@Column(name="brand_name")
	private String brandName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "brand", 
			cascade={CascadeType.REFRESH,CascadeType.MERGE}, orphanRemoval = true)
	private Set<Car> cars = new HashSet<Car>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "brand", 
			cascade={CascadeType.REFRESH,CascadeType.MERGE}, orphanRemoval = true)
	private Set<CarModel> models = new HashSet<CarModel>();
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	public Set<CarModel> getModels() {
		return models;
	}

	public void setModels(Set<CarModel> models) {
		this.models = models;
	}
	
}
