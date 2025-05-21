package com.example.letsplay.security.config;

import com.example.letsplay.user.mapper.MyUserDetailsServices;
import com.example.letsplay.user.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
        if(authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7); // Enlève "Bearer "
            email = jwtService.extractUsername(token); // Extrait l'email (username) du token
        }

//
//        Si on a pu extraire un email (donc un token valide)
//        ET aucun utilisateur n’est encore authentifié dans le SecurityContext
//       Cela signifie qu’il s’agit de la première requête authentifiée de cet utilisateur dans cette session.
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(MyUserDetailsServices.class).loadUserByUsername(email);
            if (jwtService.validateToken(token,userDetails)){
//                On crée un objet d’authentification à partir de l’utilisateur.
//                On ne met pas de mot de passe (car on ne le revalide pas ici, c’est déjà fait au login), mais on passe les authorities (rôles).
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                // On attache les détails de la requête HTTP à l’objet d’authentification (adresse IP, session, etc)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//                On place l’utilisateur authentifié dans le contexte de sécurité Spring,
//                ce qui permet à toute la suite de la requête (contrôleurs, services) de savoir qui est connecté.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // continue le filter
        filterChain.doFilter(request,response);
    }
}
