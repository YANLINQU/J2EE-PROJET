package friendsofmine.service;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Inscription;
import friendsofmine.domain.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by QYL on 2017/2/27.
 */
@Service
public class InitialisationService {
    @Autowired
    private ActiviteService activiteService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private InscriptionService inscriptionService;

    private Inscription maryOnTaekwondo, thomOnRandonnee, thomOnLindyhop;
    private Utilisateur thom,mary;
    private Activite Randonnee,Lindyhop,Taekwondo;
    public void initDonnees(){
        initUtilisateur();
        initActivite();
        initInscription();
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

    public void initInscription(){
        maryOnTaekwondo = new Inscription(mary,Taekwondo, new Date());
        thomOnRandonnee = new Inscription(thom,Randonnee,new Date());
        thomOnLindyhop = new Inscription(thom,Lindyhop,new Date());

        inscriptionService.saveInscription(maryOnTaekwondo);
        inscriptionService.saveInscription(thomOnRandonnee);
        inscriptionService.saveInscription(thomOnLindyhop);
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

    public Inscription getMaryOnTaekwondo(){
        return this.maryOnTaekwondo;
    }
    public Inscription getThomOnRandonnee(){
        return this.thomOnRandonnee;
    }
    public Inscription getThomOnLindyhop(){
        return this.thomOnLindyhop;
    }
}
