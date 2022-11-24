package sn.mndiaye.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.mndiaye.springsecurity.security.dto.AuthentificationResponseDto;
import sn.mndiaye.springsecurity.security.dto.AuthentificationDto;
import sn.mndiaye.springsecurity.entity.Utilisateur;
import sn.mndiaye.springsecurity.security.service.UtilisateurDetailService;
import sn.mndiaye.springsecurity.security.service.UtilisateurService;
import sn.mndiaye.springsecurity.security.utils.JWTUtility;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UtilisateurController {

@Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JWTUtility jwtUtility;

  @Autowired
  private UtilisateurDetailService utilisateurDetailService;

  @Autowired
  private UtilisateurService utilisateurService;

  @PostMapping("/utilisateur")
  public Utilisateur addUser(@RequestBody Utilisateur utilisateur) {
    return utilisateurService.createAdmin(utilisateur);
  }

  @PostMapping("/utilisateur/auth")
  public AuthentificationResponseDto authenticated(@RequestBody AuthentificationDto authentificationDto) throws Exception {
    
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authentificationDto.getEmail(), authentificationDto.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("INNVALID_CREDENTIALS", e);
    }
    UserDetails userDetails = utilisateurDetailService.loadUserByUsername(authentificationDto.getEmail());
    Utilisateur utilisateur = utilisateurService.getByMail(authentificationDto.getEmail());
    if (utilisateur != null) {
      final String token = jwtUtility.generateToken(utilisateur ,userDetails);
      return new AuthentificationResponseDto(utilisateur, token);
    } else {
      return null;
    }
  }



}
