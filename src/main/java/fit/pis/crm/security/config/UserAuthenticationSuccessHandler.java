package fit.pis.crm.security.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Authentication authResult)
			throws IOException, ServletException {
       
        Set<String> roles = AuthorityUtils.authorityListToSet(authResult.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/crm/admin");
        } else if (roles.contains("ROLE_MANAGER")) {
        	response.sendRedirect("/crm/manager");
        } else if (roles.contains("ROLE_SUPERVISOR")) {
        	response.sendRedirect("/crm/supervisor");
        }
		
	}

}
