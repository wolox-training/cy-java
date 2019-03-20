package wolox.training.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    public CustomAuthenticationProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByUsername(name);
//        if(user == null || !user.validatePassword(password)) {
//            throw new BadCredentialsException("Invalid credentials");
//        }else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
//        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
            UsernamePasswordAuthenticationToken.class);
    }
}
