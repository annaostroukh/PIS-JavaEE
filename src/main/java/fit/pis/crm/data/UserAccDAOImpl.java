package fit.pis.crm.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.UserAcc;

@Repository
@Transactional
public class UserAccDAOImpl implements UserAccDAO{
	
	@Autowired
	private MeetingDAO meetingDAO;

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
		
		List<UserAcc> managers = findManagers();
        
        UserAcc mostBusyManager = null;
        Integer mostBusyManagerCount = 0;

        for (UserAcc thisManager : managers) {
            Integer currentManagerMeetings = meetingDAO.findAllOrderedByDate(thisManager).size();
            if (currentManagerMeetings >= mostBusyManagerCount) {
                mostBusyManager = thisManager;
                mostBusyManagerCount = currentManagerMeetings;
            }
        }
        return mostBusyManager;
	}
	
	@Override
	public Integer findMaxManagerMeetings(){
		List<UserAcc> managers = findManagers();
        List<Integer> meetingsCount = new ArrayList<>();

        for (UserAcc manager : managers) {
            meetingsCount.add(meetingDAO.findAllOrderedByDate(manager).size());
        }

        return Collections.max(meetingsCount);
	}
	
	@Override
	public Integer findMinManagerMeetings(){
		List<UserAcc> managers = findManagers();
        List<Integer> meetingsCount = new ArrayList<>();

        for (UserAcc manager : managers) {
            meetingsCount.add(meetingDAO.findAllOrderedByDate(manager).size());
        }
        return Collections.min(meetingsCount);
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
		List<UserAcc> managers = findManagers();
        UserAcc lessBusyManager = null;
        Integer mostBusyManagerCount = findMaxManagerMeetings();

        for (UserAcc thisManager : managers) {
            Integer currentManagerMeetings = meetingDAO.findAllOrderedByDate(thisManager).size();
            if (currentManagerMeetings <= mostBusyManagerCount) {
            	lessBusyManager = thisManager;
            	mostBusyManagerCount = currentManagerMeetings;
            }
        }
        return lessBusyManager;
	}
}