package fit.pis.crm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.Car;

@Repository
@Transactional
public class CarDAOImpl implements CarDAO{
	
	@PersistenceContext(unitName = "crm-unit")
	private EntityManager em;

	@Override
	public Car findById(Long id) {
		return em.find(Car.class, id);
	}

	@Override
	public void register(Car car) {
		em.persist(car);
		
	}

	@Override
	public void deleteById(Long id) {
		Car car = findById(id);
		em.remove(car);
		
	}

	@Override
	public void update(Car car) {
		em.merge(car);
		
	}

	@Override
	public List<Car> findAllOrderedByBrand() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Car> criteria = cb.createQuery(Car.class);
		Root<Car> car = criteria.from(Car.class);
		criteria.select(car).orderBy(cb.asc(car.get("brand")));
		criteria.distinct(true);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Car findByParams(Long brandId, Long modelId, String year) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Car> criteria = cb.createQuery(Car.class);
		Root car = criteria.from(Car.class);
		criteria.select(car).where(cb.and(cb.equal(car.get("brand").get("id"), brandId)), 
				cb.equal(car.get("model").get("id"), modelId), cb.equal(car.get("year"), year));
		return em.createQuery(criteria).getSingleResult();
	}

}
