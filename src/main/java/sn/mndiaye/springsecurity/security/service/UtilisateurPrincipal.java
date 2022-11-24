package sn.mndiaye.springsecurity.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.mndiaye.springsecurity.entity.Utilisateur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtilisateurPrincipal implements UserDetails {

    private Utilisateur utilisateur;

    public UtilisateurPrincipal(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

       if(this.utilisateur != null){
           this.utilisateur.getPermissionsList().forEach(p ->{
               GrantedAuthority authority = new SimpleGrantedAuthority(p);
               authorities.add(authority);
           });

           this.utilisateur.getRolesList().forEach(r ->{
               GrantedAuthority authority = new SimpleGrantedAuthority(r);
               authorities.add(authority);
           });
           return authorities;
       }
       return authorities;
    }

    @Override
    public String getPassword() {
          return this.utilisateur.getPassword();

    }

    @Override
    public String getUsername() {
            return this.utilisateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
