package friendsofmine.service;

import friendsofmine.domain.Inscription;
import friendsofmine.repositories.ActiviteRepository;
import friendsofmine.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by QYL on 2017/3/16.
 */

@Service
public class InscriptionService {
    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription saveInscription(Inscription inscription){
        if(inscription == null){
            throw new IllegalArgumentException("Activite is must be objet");
        }
        return inscriptionRepository.save(inscription);
    }

    public Inscription findOneInscription(Long id){
        return this.inscriptionRepository.findOne(id);
    }

    public long countInscription(){
        return this.inscriptionRepository.count();
    }

    public InscriptionRepository getInscriptionRepository(){
        return this.inscriptionRepository;
    }

    public void deleteInscription(Long id){
        this.inscriptionRepository.delete(id);
    }

    public Page<Inscription> findAllInscription(Pageable pageable) {
        return inscriptionRepository.findAll(pageable);
    }

    public Page<Inscription> findAll(String nom, String titre, Pageable pageable) {
        return inscriptionRepository.findByParticipantNomOrActiviteTitreAllIgnoreCase(nom, titre , pageable);
    }
}
