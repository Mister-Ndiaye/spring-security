package sn.mndiaye.springsecurity.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.mndiaye.springsecurity.entity.Utilisateur;
import sn.mndiaye.springsecurity.repo.UtilisateurRepository;

@Service
public class UtilisateurDetailService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(username);

        if (utilisateur == null){
            throw new UsernameNotFoundException("utilisateur non trouvé essaye a");
        }
       else
            return new UtilisateurPrincipal(utilisateur);
        }


}
