package friendsofmine.service;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by QYL on 2017/2/27.
 */
@Service
public class InitialisationService {
    @Autowired
    private ActiviteService activiteService;
    @Autowired
    private UtilisateurService utilisateurService;

    Utilisateur thom,mary;
    Activite Randonnee,Lindyhop,Taekwondo;
    public void initDonnees(){
        initUtilisateur();
        initActivite();
    }

    public void initActivite(){
        Randonnee = new Activite("randonnee","descriptif1",thom);
        Lindyhop = new Activite("lindyhop","descriptif2",thom);
        Taekwondo = new Activite("taekwondo","descriptif3",mary);
        //Taekwondo = new Activite();
        this.activiteService.saveActivite(Lindyhop);
        this.activiteService.saveActivite(Randonnee);
        this.activiteService.saveActivite(Taekwondo);
    }

    public void initUtilisateur(){
        mary = new Utilisateur("mary", "yorke", "thom@yorke.fr", "M");
        thom = new Utilisateur("thom", "yorke", "thom@yorke.fr", "M");
        utilisateurService.saveUtilisateur(mary);
        utilisateurService.saveUtilisateur(thom);
    }

    public Utilisateur getThom() {
        return thom;
    }

    public Utilisateur getMary() {
        return mary;
    }

    public Activite getRandonnee() {
        return Randonnee;
    }

    public Activite getLindyhop() {
        return Lindyhop;
    }

    public Activite getTaekwondo() {
        return Taekwondo;
    }
}
