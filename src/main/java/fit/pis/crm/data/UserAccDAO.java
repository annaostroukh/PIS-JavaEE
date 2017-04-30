package fit.pis.crm.data;

import java.util.Date;
import java.util.List;

import fit.pis.crm.model.UserAcc;

public interface UserAccDAO {
	public UserAcc findById(Long id);

    public UserAcc findByEmail(String email);

    public List<UserAcc> findAllOrderedByUserName();
    
    public List<UserAcc> findManagers();
    
    public UserAcc findManagerWithMaxLoad();
    
    public UserAcc findManagerWithMinLoad();
    
    public List<UserAcc> findManagersWeekMeetings(Date startDate, Date endDate);
    
    public List<UserAcc> findManagersTodayMeetings(Date today);

    public void register(UserAcc userAccount);
    
    public void deleteById(Long id);
    
    public void update(UserAcc userAccount);
}
