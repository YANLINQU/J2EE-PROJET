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
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @NotNull
    @Size(min=1, max=16)
    public String firstName;
    @NotNull
    @Size(min=1, max=16)
    public String secondeName;
    @NotNull
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{invalid.adresse}")
    @Size(min=1, max=30)
    public String adresse;
    @NotNull
    @Pattern(regexp="M|F")
    public String sex;

    public Date date;

    @OneToMany(mappedBy="utilisateur")
    public Set<Activite> activites = new HashSet<Activite>();
    public void addActivite(Activite a) {activites.add(a) ;}
    public Set<Activite> getActivites() {return activites;}

    public Utilisateur(String firstName,String secondeName,String adresse,String sex){
        this.firstName = firstName;
        this.secondeName = secondeName;
        this.adresse = adresse;
        this.sex = sex;
        this.date = new Date();
    }

    public Utilisateur(String firstName,String secondeName,String adresse,String sex,Date date){
        this.firstName = firstName;
        this.secondeName = secondeName;
        this.adresse = adresse;
        this.sex = sex;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondeName() {
        return secondeName;
    }

    public void setSecondeName(String secondeName) {
        this.secondeName = secondeName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setActivites(Set<Activite> activites) {
        this.activites = activites;
    }

    public void delActivites(){
        this.activites.clear();
    }
}
