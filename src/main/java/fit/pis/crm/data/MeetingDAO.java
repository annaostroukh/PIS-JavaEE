package fit.pis.crm.data;

import java.util.Date;
import java.util.List;

import fit.pis.crm.model.Meeting;
import fit.pis.crm.model.UserAcc;

public interface MeetingDAO {
	public  Meeting findById(Long id);
	
	public List<Meeting> findAllOrderedByDate(UserAcc manager);
	
	public List<Meeting> findAllInMonth(String year, String month);
	
	public List<Meeting> findBetweenDates(Date start, Date end);
	
	public List<Meeting> findToday(Date today);

    public void register(Meeting meeting);
    
    public void deleteById(Long id);
    
    public void update(Meeting meeting);

}
