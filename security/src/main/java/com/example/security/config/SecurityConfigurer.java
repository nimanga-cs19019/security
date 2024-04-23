package com.example.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    private final UserDetailsService userDetailsService;

    public SecurityConfigurer(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/*.png", "/*.ico", "/*.xml", "/*.json", "/packages/**", "/images/**", "/css/**",
                                "/js/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(
                        oauth2Login -> oauth2Login
                                .loginPage("/index")
                                .defaultSuccessUrl("/index", true)
                                .failureUrl("/login?error"))
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/"));
        return http.build();
    }
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        // Define and return your OAuth2 client registrations here
        // Example for GitHub:
        return new InMemoryClientRegistrationRepository(
                ClientRegistration.withRegistrationId("github")
                        .clientId("your-github-client-id")
                        .clientSecret("your-github-client-secret")
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                        .scope("user:read")
                        .authorizationUri("https://github.com/login/oauth/authorize")
                        .tokenUri("https://github.com/login/oauth/access_token")
                        .userInfoUri("https://api.github.com/user")
                        .userNameAttributeName("id")
                        .build()
        );
    }
}
