package fit.pis.crm.data;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.UserAcc;

@Repository
@Transactional
public class UserAccDAOImpl implements UserAccDAO{

	@PersistenceContext(unitName = "crm-unit")
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

	@Override
	public List<UserAcc> findManagers() {
		String manager = "ROLE_MANAGER";
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAcc> criteria = cb.createQuery(UserAcc.class);
		Root<UserAcc> userAccount = criteria.from(UserAcc.class);
		criteria.select(userAccount).where(cb.equal(userAccount.get("role"), manager)).orderBy(cb.asc(userAccount.get("username")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public UserAcc findManagerWithMaxLoad(String role) {
//		String manager = role;
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<UserAcc> criteria = cb.createQuery(UserAcc.class);
//		Root<UserAcc> userAccount = criteria.from(UserAcc.class);
//		// criteria.multiselect(cb.max(cb.count(userAccount.get("meetings")))).orderBy(cb.asc(userAccount.get("username")));
//		criteria.select(userAccount).where(cb.equal(userAccount.get("role"), manager))
//			.where(cb.equal((cb.count(userAccount.get("meetings"))), cb.max(cb.count(userAccount.get("meetings")))));
//		return em.createQuery(criteria).getSingleResult();
		return null;
	}
	
	@Override
	public List<Number> findMaxManagerMeetings(){
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Number> criteria = cb.createQuery(Number.class);
//		Root<UserAcc> userAccount = criteria.from(UserAcc.class);
//		criteria.multiselect(cb.max(cb.count(userAccount.get("meetings"))));
//		//criteria.select(cb.max(cb.count(userAccount.get("meetings"))));
//		return em.createQuery(criteria).getResultList();
		return null;
	}

	

	@Override
	public List<UserAcc> findManagersWeekMeetings(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAcc> findManagersTodayMeetings(Date today) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAcc findManagerWithMinLoad() {
		// TODO Auto-generated method stub
		return null;
	}

}
