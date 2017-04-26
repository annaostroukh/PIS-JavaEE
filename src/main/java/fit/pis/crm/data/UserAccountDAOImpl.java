package fit.pis.crm.data;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.UserAccount;

@Repository
@Transactional
@Stateful
public class UserAccountDAOImpl implements UserAccountDAO {
	
	@PersistenceContext(unitName = "crm-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public UserAccount findById(Long id) {
		return em.find(UserAccount.class, id);
	}
	
	public void register(UserAccount userAccount) {
		em.persist(userAccount);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String hashSource = userAccount.getEmail() + userAccount.getUserName() + userAccount.getSurname() + userAccount.getPassword() 
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

	public List<UserAccount> findAllOrderedByUserName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAccount> criteria = cb.createQuery(UserAccount.class);
		Root<UserAccount> userAccount = criteria.from(UserAccount.class);
		criteria.select(userAccount).orderBy(cb.asc(userAccount.get("userName")));
		return em.createQuery(criteria).getResultList();
	}
	
	public UserAccount findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAccount> criteria = cb.createQuery(UserAccount.class);
		Root<UserAccount> userAccount = criteria.from(UserAccount.class);
		criteria.select(userAccount).where(cb.equal(userAccount.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}

	public void update(UserAccount userAccount) {
		em.merge(userAccount);
		
	}
	
	public void deleteById(Long id) {
		UserAccountDAO userAccount = (UserAccountDAO) findById(id);
		em.remove(userAccount);
	}
	
	

}
