package friendsofmine.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by QYL on 2017/2/27.
 */
@Entity
public class Activite implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=1, max=16)

    private String titre;

    private String descriptif;
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    private Utilisateur utilisateur;

    public Activite(String titre,String descriptif,Utilisateur utilisateur){
        this.titre = titre;
        this.descriptif = descriptif;
        this.utilisateur=utilisateur;
    }

    public Activite(String titre,String descriptif ){
        this.titre = titre;
        this.descriptif = descriptif;
    }

    public Activite(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
