package fitnesstracker.developers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DeveloperUserDetailsServiceImpl implements UserDetailsService {
    private final DeveloperRepository repository;

    public DeveloperUserDetailsServiceImpl(DeveloperRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Developer developer = repository
                .findByEmail(username.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new UsernameNotFoundException("Username '" + username + "' not found"));
        return new DeveloperAdapter(developer);
    }
}
