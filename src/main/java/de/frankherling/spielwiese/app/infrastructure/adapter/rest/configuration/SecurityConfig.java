package de.frankherling.spielwiese.app.infrastructure.adapter.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .requestMatchers("/api/orders", "/api/orders/**").hasRole("APPUSER")
                                        .requestMatchers("/api/payments", "/api/payments/**").hasRole("APPUSER")
                                        .requestMatchers("/**").permitAll()
//                                .requestMatchers("/api/public").permitAll()
//                                .requestMatchers("/swagger-ui.html").permitAll()
//                                .requestMatchers("/api-docs/**").permitAll()
//                                .requestMatchers("/api/private").authenticated()
//                                .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
//                                .requestMatchers("/**").hasRole("USER")
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
                ).csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(jwtConfigurer -> {
                            JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                            jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomJwtAuthenticationConverter());
                            jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter);
                        }));


        return http.build();
    }

}
