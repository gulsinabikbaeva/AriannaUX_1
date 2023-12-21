package cp.webapp.web.config;

import cp.webapp.web.model.User;
import cp.webapp.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private ItemService itemService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = itemService.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList (user.getRole().getName());
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, auth);
	}

}