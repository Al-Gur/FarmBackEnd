package telran.java57.farmbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dao.UserAccountRepository;
import telran.java57.farmbackend.accounting.dto.exceptions.UserNotFoundException;
import telran.java57.farmbackend.accounting.model.UserAccount;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Collection<String> authorities = user.getRoles().stream().map(r -> "ROLE_" + r).toList();
        return new User(username, user.getPassword(), AuthorityUtils.createAuthorityList(authorities));
    }
}
