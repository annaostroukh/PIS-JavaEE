package fit.pis.crm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.Brand;

@Repository
@Transactional
public class BrandDAOImpl implements BrandDAO{
	
	@PersistenceContext(unitName = "crm-unit")
	EntityManager em;

	@Override
	public List<Brand> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Brand> criteria = cb.createQuery(Brand.class);
		Root<Brand> brand = criteria.from(Brand.class);
		
		criteria.select(brand).orderBy(cb.asc(brand.get("brandName")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Brand findById(Long id) {
		Brand brand = (Brand) em.find(Brand.class, id);
		return brand;
	}

	@Override
	public Brand findByName(String name) {
		Brand brand = (Brand) em.find(Brand.class, name);
		return brand;
	}

	@Override
	public void register(Brand brand) {
		em.persist(brand);
		
	}

	@Override
	public void update(Brand brand) {
		em.merge(brand);
		
	}

	@Override
	public void deleteById(Long id) {
		Brand brand = findById(id);
		em.remove(brand);
		
	}
	
	
}
