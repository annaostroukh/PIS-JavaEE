package fit.pis.crm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.Client;
import fit.pis.crm.model.UserAcc;

@Repository
@Transactional
public class ClientDAOImpl implements ClientDAO {

	@PersistenceContext(unitName = "crm-unit")
	private EntityManager em;
	
	@Override
	public Client findById(Long id) {
		return em.find(Client.class, id);
	}

	@Override
	public Client findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> client = criteria.from(Client.class);
		criteria.select(client).where(cb.equal(client.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public Client findByManager(UserAcc manager) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> client = criteria.from(Client.class);
		criteria.select(client).where(cb.equal(client.get("managers"), manager));
		return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public List<Client> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> client = criteria.from(Client.class);
		criteria.select(client).orderBy(cb.asc(client.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public List<Client> findAllForManager(UserAcc manager) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> client = criteria.from(Client.class);
		criteria.select(client)
			.where(cb.equal(client.get("managers"), manager))
			.orderBy(cb.asc(client.get("name")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void register(Client client) {
		em.persist(client);
		
	}

	@Override
	public void deleteById(Long id) {
		Client client = findById(id);
		em.remove(client);
		
	}

	@Override
	public void update(Client client) {
		em.merge(client);
		
	}

	@Override
	public List<Client> findAllWithoutManager() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> client = criteria.from(Client.class);
		criteria.select(client).where(cb.equal(client.get("managers"), null)).orderBy(cb.asc(client.get("name")));
		return em.createQuery(criteria).getResultList();
	}

}
