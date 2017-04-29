package fit.pis.crm.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.pis.crm.model.Meeting;
import fit.pis.crm.model.UserAcc;

@Repository
@Transactional
public class MeetingDAOImpl implements MeetingDAO {

	@PersistenceContext(unitName = "crm-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public Meeting findById(Long id) {
		return em.find(Meeting.class, id);
	}

	@Override
	public List<Meeting> findAllOrderedByDate(UserAcc manager) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Meeting> criteria = cb.createQuery(Meeting.class);
		Root<Meeting> meeting = criteria.from(Meeting.class);
		criteria.select(meeting)
			.where(cb.equal(meeting.get("manager"), manager))
			.orderBy(cb.asc(meeting.get("date")));
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public List<Meeting> findAllInMonth(String year, String month){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
			Date startDate = dateFormat.parse(year + "/" + month);
			Calendar c = Calendar.getInstance();
			c.setTime(startDate);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date endDate = c.getTime();
			return this.findBetweenDates(startDate, endDate);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public List<Meeting> findBetweenDates(Date start, Date end){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Meeting> criteria = cb.createQuery(Meeting.class);
		Root<Meeting> meeting = criteria.from(Meeting.class);
		criteria.select(meeting)
				.where(cb.and(cb.greaterThanOrEqualTo(meeting.<Date>get("startDate"), start),
						cb.lessThanOrEqualTo(meeting.<Date>get("endDate"), end)))
				.orderBy(cb.asc(meeting.get("startDate")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void register(Meeting meeting) {
		em.persist(meeting);
		
	}

	@Override
	public void deleteById(Long id) {
		Meeting meeting = findById(id);
		em.remove(meeting);
		
	}

	@Override
	public void update(Meeting meeting) {
		em.merge(meeting);
		
	}

}
