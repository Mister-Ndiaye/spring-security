package sn.mndiaye.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.mndiaye.springsecurity.entity.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    Utilisateur findByTelephone(String telephone);
    Utilisateur findByEmail(String email);
}
