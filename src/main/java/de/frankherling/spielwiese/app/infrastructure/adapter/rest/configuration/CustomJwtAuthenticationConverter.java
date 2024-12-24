package de.frankherling.spielwiese.app.infrastructure.adapter.rest.configuration;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = defaultGrantedAuthoritiesConverter.convert(jwt);

        LinkedTreeMap<String, List> realmAccess = jwt.getClaim("realm_access");

        List<GrantedAuthority> customAuthorities = ((List<String>) realmAccess.get("roles")).stream()
                .map(role -> "ROLE_" + role.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        authorities.addAll(customAuthorities);

        return authorities;
    }
}
