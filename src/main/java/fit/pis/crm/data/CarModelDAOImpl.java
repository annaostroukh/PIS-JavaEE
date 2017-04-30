package fit.pis.crm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.CarModel;

@Repository
@Transactional
public class CarModelDAOImpl implements CarModelDAO {

	@PersistenceContext(unitName = "crm-unit")
	EntityManager em;
	
	@Override
	public List<CarModel> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CarModel> criteria = cb.createQuery(CarModel.class);
		Root<CarModel> model = criteria.from(CarModel.class);
		
		criteria.select(model).orderBy(cb.asc(model.get("modelName")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public CarModel findById(Long id) {
		CarModel model = (CarModel) em.find(CarModel.class, id);
		return model;
	}

	@Override
	public CarModel findByName(String name) {
		CarModel model = (CarModel) em.find(CarModel.class, name);
		return model;
	}

	@Override
	public void register(CarModel model) {
		em.persist(model);
		
	}

	@Override
	public void update(CarModel model) {
		em.merge(model);
		
	}

	@Override
	public void deleteById(Long id) {
		CarModel model = findById(id);
		em.remove(model);
		
	}

}
