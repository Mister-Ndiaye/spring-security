package sn.mndiaye.springsecurity.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.mndiaye.springsecurity.entity.Utilisateur;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationResponseDto {

    private Utilisateur utilisateur;
    private String token;
}
