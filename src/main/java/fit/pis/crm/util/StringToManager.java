package fit.pis.crm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import fit.pis.crm.data.UserAccDAO;
import fit.pis.crm.model.UserAcc;

public final class StringToManager implements Converter<String, UserAcc>{
	
	@Autowired
	UserAccDAO userAccDAO;

	@Override
	public UserAcc convert(String source) {
		if (source == null || source.isEmpty()) {
            return null;
        }
		return userAccDAO.findById(Long.parseLong(source));
	}
	
	

}
