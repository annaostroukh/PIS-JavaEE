package fit.pis.crm.data;

import java.util.List;

import fit.pis.crm.model.CarModel;

public interface CarModelDAO {
	
	public List<CarModel> findAllOrderedByName();
	
	public CarModel findById(Long id);
	
	public CarModel findByName(String name);
	
	public void register(CarModel model);
	
	public void update(CarModel model);
	
	public void deleteById(Long id);

}
