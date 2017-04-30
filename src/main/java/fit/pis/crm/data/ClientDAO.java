package fit.pis.crm.data;

import java.util.List;

import fit.pis.crm.model.Client;
import fit.pis.crm.model.UserAcc;

public interface ClientDAO {
	public Client findById(Long id);

    public Client findByEmail(String email);
    
    public Client findByManager(UserAcc manager);

    public List<Client> findAllOrderedByName();
    
    public List<Client> findAllWithoutManager();

    public void register(Client client);
    
    public void deleteById(Long id);
    
    public void update(Client client);

}
