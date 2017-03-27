package friendsofmine.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by QYL on 2017/3/16.
 */
@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Utilisateur utilisateur_ins;
    public Utilisateur getParticipant() { return utilisateur_ins; }
    public void setParticipant(Utilisateur utilisateur) { this.utilisateur_ins = utilisateur; }

    @NotNull
    @ManyToOne
    private Activite activite_ins;
    public Activite getActivite() { return activite_ins; }
    public void setActivite(Activite activite) { this.activite_ins = activite; }

    private Date dateInscription;

    public Inscription(Utilisateur util,Activite act,Date date){
        this.utilisateur_ins = util;
        this.activite_ins = act;
        if(date!=null){
            this.dateInscription = date;
        }else{
            this.dateInscription = new Date();
        }
    }

    public Inscription(){

    }
    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", participant=" + utilisateur_ins +
                ", activite=" + activite_ins +
                '}';
    }
}
