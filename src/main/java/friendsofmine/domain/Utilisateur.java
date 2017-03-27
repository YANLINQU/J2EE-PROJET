package friendsofmine.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by QYL on 2017/2/27.
 */
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @NotNull
    @Size(min=1, max=16)
    public String nom;
    @NotNull
    @Size(min=1, max=16)
    public String prenom;
    @NotNull
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{invalid.adresse}")
    @Size(min=1, max=30)
    public String email;
    @NotNull
    @Pattern(regexp="M|F")
    public String sexe;

    public Date date;

    @OneToMany(mappedBy="utilisateur")
    public Set<Activite> activites = new HashSet<Activite>();
    public void addActivite(Activite a) {activites.add(a) ;}
    public Set<Activite> getActivites() {return activites;}
    public void setActivites(Set<Activite> activites) {
        this.activites = activites;
    }

    @OneToMany(mappedBy = "utilisateur_ins")
    private Set<Inscription> inscriptions = new HashSet <Inscription >();
    public Set<Inscription> getInscriptions() { return this.inscriptions; }
    public void setInscriptions(Set<Inscription> inscriptions) { this.inscriptions = inscriptions; }

    public Utilisateur(String nom,String prenom,String email,String sexe){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.date = new Date();
    }

    public Utilisateur(String nom,String prenom,String email,String sexe,Date date){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.date = date;
    }

    public Utilisateur(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void delActivites(){
        this.activites.clear();
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null)
            return false;
        return sexe != null ? sexe.equals(that.sexe) : that.sexe == null;

    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (sexe != null ? sexe.hashCode() : 0);
        return result;
    }
}
