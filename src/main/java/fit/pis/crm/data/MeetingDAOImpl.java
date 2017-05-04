package fit.pis.crm.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

	@PersistenceContext(unitName = "crm-unit")
	private EntityManager em;
	
	@Override
	public Meeting findById(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Meeting> criteria = cb.createQuery(Meeting.class);
		Root<Meeting> meeting = criteria.from(Meeting.class);
		criteria.select(meeting).where(cb.equal(meeting.get("id"), id));
		System.out.println(em.createQuery(criteria).getSingleResult());
		return em.createQuery(criteria).getSingleResult();
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
	public void register(Meeting meeting) {
		System.out.println(meeting);
		em.persist(meeting);
		
	}

	@Override
	public void deleteById(Long id) {
		Meeting meeting = findById(id);
		System.out.println(meeting);
		em.remove(meeting);
		
	}

	@Override
	public void update(Meeting meeting) {
		em.merge(meeting);
		
	}

	@Override
	public List<Meeting> findBetweenDates(Date start, Date end) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Meeting> criteria = cb.createQuery(Meeting.class);
		Root<Meeting> meeting = criteria.from(Meeting.class);
		criteria.select(meeting)
				.where(cb.and(cb.greaterThanOrEqualTo(meeting.<Date>get("date"), start),
						cb.lessThanOrEqualTo(meeting.<Date>get("date"), end)))
				.orderBy(cb.asc(meeting.get("date")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Meeting> findToday(Date date) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Meeting> criteria = cb.createQuery(Meeting.class);
		Root<Meeting> meeting = criteria.from(Meeting.class);
		criteria.select(meeting).where(cb.equal(meeting.get("date"), date));
		return em.createQuery(criteria).getResultList();
	}

}
