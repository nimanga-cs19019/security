package com.example.security.Dao;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Repository
public class UserDao {
    private final static List<UserDetails> APPLICATO_USERSES= Arrays.asList(
            new User("pivindinimanga@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))

            ),
            new User("user.mail@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))

    );
    public UserDetails findUserByEmail(String email)
    {
        return APPLICATO_USERSES
                .stream()
                .filter(u->u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException("No user was found"));
    }
}
