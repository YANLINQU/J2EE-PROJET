package friendsofmine;


import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.service.ActiviteService;
import friendsofmine.service.InitialisationService;
import friendsofmine.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Created by QYL on 2017/2/27.
 */
@Component
public class Bootstrap {
    @Autowired
    private InitialisationService initialisationService;
    @Autowired
    private ActiviteService activiteService;
    @Autowired
    private UtilisateurService utilisateurServices;

    @PostConstruct
    public void init(){
        try {
            this.initialisationService.initDonnees();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public InitialisationService getInitialisationService(){
        return this.initialisationService;
    }

    public ArrayList<Activite> getAllActivite(){
        return this.activiteService.findAllActivite();
    }

    public ArrayList<Utilisateur> getAllUtilisateur(){
        return this.utilisateurServices.findAllUtilisateur();
    }

    public UtilisateurService getUtilisateurServices(){
        return this.utilisateurServices;
    }
}
