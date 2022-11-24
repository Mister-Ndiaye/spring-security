package sn.mndiaye.springsecurity.security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.mndiaye.springsecurity.entity.Utilisateur;
import sn.mndiaye.springsecurity.repo.UtilisateurRepository;

import java.util.List;

@Service
public class UtilisateurService {

    final private UtilisateurRepository utilisateurRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAll(){return utilisateurRepository.findAll();}

    public Utilisateur createAdmin(Utilisateur utilisateur){
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setPrenom(utilisateur.getPrenom());
        utilisateur1.setNom(utilisateur.getNom());
        utilisateur1.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur1.setTelephone(utilisateur.getTelephone());
        utilisateur1.setRoles(utilisateur.getRoles());
        utilisateur1.setEmail(utilisateur.getEmail());
        utilisateur1.setPermissions(utilisateur.getPermissions());
        return utilisateurRepository.save(utilisateur1);
    }

    public Utilisateur getByMail(String email){
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur getbyTelephone(String telephone){
        return utilisateurRepository.findByTelephone(telephone);
    }
}
