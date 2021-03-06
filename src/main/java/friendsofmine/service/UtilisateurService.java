package friendsofmine.service;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.repositories.ActiviteRepository;
import friendsofmine.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by QYL on 2017/3/6.
 */
@Service
public class UtilisateurService {
    private MembreRepository utilisateurRepository;
    @Autowired
    public void setMembreRepository(MembreRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public void saveUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }
    public Utilisateur findOneUtilisateur(Long id){
        return utilisateurRepository.findOne(id);
    }
    public long countUtilisateur(){
        return utilisateurRepository.count();
    }
    public MembreRepository getUtilisateurRepository(){
        return this.utilisateurRepository;
    }
    public void delUtilisateur(Long id){
        this.utilisateurRepository.delete(id);
    }

    public ArrayList<Utilisateur> findAllUtilisateurs(){
        ArrayList<Utilisateur> listActivite=new ArrayList<Utilisateur>();
        for(Iterator itr = utilisateurRepository.findAll().iterator(); itr.hasNext();){
            listActivite.add((Utilisateur) itr.next());
        }
        return listActivite;
    }

    public ArrayList<Utilisateur> findUtilisateursM() {
        Utilisateur homme = new Utilisateur();
        homme.setSexe("M");
        Example<Utilisateur> example = Example.of(homme);

        return convert(utilisateurRepository.findAll(example));
    }

    public ArrayList<Utilisateur> findUtilisateursF() {
        Utilisateur femme = new Utilisateur();
        femme.setSexe("F");
        Example<Utilisateur> example = Example.of(femme);

        return convert(utilisateurRepository.findAll(example));
    }

    private ArrayList<Utilisateur> convert(Iterable<Utilisateur> utilisateurs) {
        ArrayList<Utilisateur> utilisateurList = new ArrayList<>();
        utilisateurs.forEach(utilisateurList::add);
        return utilisateurList;
    }
}
