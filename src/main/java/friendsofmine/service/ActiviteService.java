package friendsofmine.service;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.repositories.ActiviteRepository;
import friendsofmine.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by QYL on 2017/3/6.
 */

@Service
public class ActiviteService{

    private Utilisateur utilisateur ;
    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    private MembreRepository utilisateurRepository;

    public Iterable <Activite> listAllActivites() {
        return activiteRepository.findAll();
    }

    public Activite findOneActivite(Long id) {
//to fetch a product by its id value (provided by Spring Data JPA)
        return activiteRepository.findOne(id);
    }

    public Activite saveActivite(Activite activite) {
        if(activite == null){
            throw new IllegalArgumentException("Activite is must be objet");
        }
        this.utilisateur = activite.getUtilisateur();
        this.utilisateur.addActivite(activite);
        utilisateurRepository.save(this.utilisateur);
//Spring Data JPA provides a method for saving entities
        return activiteRepository.save(activite);
    }

    public void deleteActivite(Long id) {
//provided by Spring Data JPA
        activiteRepository.delete(id);
    }

    public long countActivite(){
        return activiteRepository.count();
    }

    public ActiviteRepository getActiviteRepository(){
        return  activiteRepository;
    }

    public ArrayList<Activite> findAllActivite(){
        ArrayList<Activite> listActivite=new ArrayList<Activite>();
        for(Iterator itr = activiteRepository.findAll().iterator(); itr.hasNext();){
            listActivite.add((Activite) itr.next());
        }
        return listActivite;
    }
}
