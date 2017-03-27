package friendsofmine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by QYL on 2017/2/27.
 */
@Entity
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=1, max=16)
    private String titre;

    private String descriptif;
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "activite_ins")
    private Set<Inscription> inscriptions = new HashSet<Inscription >();
    public Set<Inscription> getInscriptions() { return this.inscriptions; }
    public void setInscriptions(Set<Inscription> inscriptions) { this.inscriptions = inscriptions; }

    public Activite(String titre, String descriptif, Utilisateur utilisateur){
        this.titre = titre;
        this.descriptif = descriptif;
        this.utilisateur=utilisateur;
    }

    public Activite(String titre, String descriptif ){
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

    @Override
    public String toString() {
        return "Activite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", descriptif='" + descriptif + '\'' +
                '}';
    }
}
