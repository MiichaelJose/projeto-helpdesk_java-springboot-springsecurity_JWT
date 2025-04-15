package com.helpdesk.config;

import com.helpdesk.config.filters.FiltroAutenticacaoCustomizado;
import com.helpdesk.config.filters.FiltroAutorizacaoCustomizado;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class Security {

    private final AuthenticationConfiguration authenticationConfiguration;

    public Security(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        FiltroAutenticacaoCustomizado customAuthenticationFilter = new FiltroAutenticacaoCustomizado(
                authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/Users/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/Users", "/Users/solicitando-acesso")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/Users/{id}").hasAnyAuthority("ADMIN", "USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/Users/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/Users/{id}/alterar-acesso").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/Users/{id}").hasAnyAuthority("ADMIN", "USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new FiltroAutorizacaoCustomizado(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
