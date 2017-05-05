package fit.pis.crm.data;

import java.util.List;

import fit.pis.crm.model.Car;

public interface CarDAO {
	
	public  Car findById(Long id);
	
	public  Car findByParams(Long brandId, Long modelId, String year);
	
	public List<Car> findAllOrderedByBrand();

    public void register(Car car);
    
    public void deleteById(Long id);
    
    public void update(Car car);

}
