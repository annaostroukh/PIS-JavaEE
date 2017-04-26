package fit.pis.crm.data;

import java.util.List;
import fit.pis.crm.model.UserAccount;

public interface UserAccountDAO {
	public UserAccount findById(Long id);

    public UserAccount findByEmail(String email);

    public List<UserAccount> findAllOrderedByUserName();

    public void register(UserAccount userAccount);
    
    public void deleteById(Long id);
    
    public void update(UserAccount userAccount);

}
