package fit.pis.crm.data;

import java.util.List;

import fit.pis.crm.model.Brand;

public interface BrandDAO {
	
	public List<Brand> findAllOrderedByName();
	
	public Brand findById(Long id);
	
	public Brand findByName(String name);
	
	public void register(Brand brand);
	
	public void update(Brand brand);
	
	public void deleteById(Long id);

}
