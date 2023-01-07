package com.jobpanel.jobpanel.business.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobpanel.jobpanel.business.dto.user.UserAuthenticatedDTO;
import com.jobpanel.jobpanel.business.entity.Role;
import com.jobpanel.jobpanel.business.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService){
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is {}",username);log.info("Password is {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String access_token = createAccessToken(user,algorithm,request);
        String refresh_token = createRefreshToken(user,algorithm,request);

        Map<String,Object> userAuthenticated = new HashMap<>();
        userAuthenticated.put("user",getUserAuthenticatedDTO(access_token, refresh_token, user.getUsername()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(),userAuthenticated);
    }

    protected String createAccessToken(User user, Algorithm algorithm, HttpServletRequest request) {
        return JWT.create().
                withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    protected String createRefreshToken(User user, Algorithm algorithm, HttpServletRequest request){
       return JWT.create().
                withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
    }

    protected UserAuthenticatedDTO getUserAuthenticatedDTO(String access_token, String refresh_token, String username){
        com.jobpanel.jobpanel.business.entity.User user = userService.getUser(username);

        Long id = user.getId();
        String role = getUserRole(user.getRoles());

        return new UserAuthenticatedDTO(
                id,
                access_token,
                refresh_token,
                username,
                role
        );
    }

    protected String getUserRole(Collection<Role> roles) {
        return roles.stream().findFirst().get().getName();
    }
}
