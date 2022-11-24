package sn.mndiaye.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idadmin")
    private  int id ;
    @Column( nullable = false )
    private String nom ;
    @Column( nullable = false )
    private String prenom ;
    private String email;
    @Column(name = "username"  ,nullable = false )
    private  String telephone  ;
    @Column( unique = true  ,nullable = false )
    private String password ;
    private String roles;
    private String permissions;

    public List<String> getRolesList(){
        if (this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionsList(){
        if (this.permissions.length()>0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
