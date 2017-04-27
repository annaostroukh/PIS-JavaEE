package fit.pis.crm.data;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.UserAcc;

@Repository
@Transactional
public class UserAccDAOImpl implements UserAccDAO{

	@PersistenceContext(unitName = "crm-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public UserAcc findById(Long id) {
		return em.find(UserAcc.class, id);
	}

	@Override
	public UserAcc findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAcc> criteria = cb.createQuery(UserAcc.class);
		Root<UserAcc> userAccount = criteria.from(UserAcc.class);
		criteria.select(userAccount).where(cb.equal(userAccount.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public List<UserAcc> findAllOrderedByUserName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAcc> criteria = cb.createQuery(UserAcc.class);
		Root<UserAcc> userAccount = criteria.from(UserAcc.class);
		criteria.select(userAccount).orderBy(cb.asc(userAccount.get("username")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void register(UserAcc userAccount) {
		em.persist(userAccount);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String hashSource = userAccount.getEmail() + userAccount.getUsername() + userAccount.getSurname() + userAccount.getPassword() 
					 + userAccount.getRole() + userAccount.getPhoneNumber() + userAccount.getDate();
			md.update(hashSource.getBytes("UTF-8"));
			em.merge(userAccount);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return;
		
	}

	@Override
	public void deleteById(Long id) {
		UserAcc userAccount = findById(id);
		em.remove(userAccount);
		
	}

	@Override
	public void update(UserAcc userAccount) {
		em.merge(userAccount);
		
	}

}
