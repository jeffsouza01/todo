package com.porto.todo.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.porto.todo.user.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks")) {

            var auth = request.getHeader("Authorization");

            var authDecoded = auth.substring(("Basic").length()).trim();

            var decoding = Base64.getDecoder().decode(authDecoded);

            var userPass = new String(decoding);

            String[] credentials = userPass.split(":");

            String username = credentials[0];
            String pass = credentials[1];

            //user validating
            var userAuthenticated = userRepository.findByUsername(username);
            if (userAuthenticated == null) {
                response.sendError(401);
            } else {
                var passVerify = BCrypt.verifyer().verify(pass.toCharArray(), userAuthenticated.getPassword());
                if (passVerify.verified) {
                    request.setAttribute("idUser", userAuthenticated.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            }

        } else {
            filterChain.doFilter(request, response);

        }


    }
}
