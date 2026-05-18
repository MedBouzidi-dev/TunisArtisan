package com.example.tunisartisan.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.tunisartisan.Services.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider authProvider) throws Exception {
        http
                .authenticationProvider(authProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/webjars/**", "/style3.css", "/login", "/register", "/css/**", "/js/**", "/images/**","/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/services", true)
                        .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/pasautorise")
                );
        return http.build();
    }

    // Les authorisations :  Chaine de filtres de sécurité

    //  la méthode  configure() qui prend en paramètre un objet  HTTPSecurity pour faire passer toutes les requêtes HTTP à
    //  travers la chaîne de filtres de sécurité, et configurez le formulaire de connexion par défaut avec la méthode
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // authorisation des requêttes
                .antMatchers("/admin").hasRole("ADMIN") // sur des ressources verouillées par le role ADMIN
                .antMatchers("/user").hasRole("USER") // sur des ressources verouillées par le role USER
                .anyRequest().authenticated() // et sur toute requêtte qui nécessite une authentification
                .and()
                .formLogin() // provenant du fomulaire de login
                .and()
                .oauth2Login();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/
}