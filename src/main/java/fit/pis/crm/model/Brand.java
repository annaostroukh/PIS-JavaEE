package fit.pis.crm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="brand")
public class Brand implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "brand_id")
	@GeneratedValue
	private Long id;

	@NotNull
	@Column(name="name")
	@Size(max = 25)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
	private Set<Car> cars = new HashSet<Car>();
	
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

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
}
