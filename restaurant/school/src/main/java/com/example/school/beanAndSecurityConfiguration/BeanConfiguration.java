package com.example.school.beanAndSecurityConfiguration;

import com.example.school.beanAndSecurityConfiguration.security.BearerTokenFilter;
import com.example.school.beanAndSecurityConfiguration.security.JwtAuthenticationFilter;
import com.example.school.beanAndSecurityConfiguration.security.JwtService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class BeanConfiguration {
    private final JwtService jwtService;

    public BeanConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc, AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        AuthenticationManager authenticationManager = authenticationManagerBuilder.getOrBuild();
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtService);
        BearerTokenFilter bearerTokenFilter = new BearerTokenFilter(jwtService);
        
        PathRequest.H2ConsoleRequestMatcher h2ConsoleRequestMatcher = PathRequest.toH2Console();
        http.authorizeHttpRequests(requests->requests.requestMatchers(h2ConsoleRequestMatcher).permitAll());
        http.csrf(csrf -> csrf.ignoringRequestMatchers(h2ConsoleRequestMatcher));
        http.headers(
                config -> config.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                )
        );


        http.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(HttpMethod.POST, "/api/products")).hasRole("ADMIN").requestMatchers(mvc.pattern("/api/products")).hasAnyRole("USER", "ADMIN").anyRequest().permitAll());
        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtAuthenticationFilter, AuthorizationFilter.class);
        http.addFilterBefore(bearerTokenFilter, AuthorizationFilter.class);
        return http.build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }


}
