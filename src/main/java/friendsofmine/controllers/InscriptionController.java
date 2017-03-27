package friendsofmine.controllers;

import friendsofmine.domain.Activite;
import friendsofmine.domain.Inscription;
import friendsofmine.domain.Utilisateur;
import friendsofmine.repositories.ActiviteRepository;
import friendsofmine.repositories.MembreRepository;
import friendsofmine.service.ActiviteService;
import friendsofmine.service.InscriptionService;
import friendsofmine.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import friendsofmine.exceptions.InscriptionNotFoundException;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by QYL on 2017/3/16.
 */
@Controller
public class InscriptionController {

    @Autowired
    private ActiviteRepository activiteRepository;

    @Autowired
    private MembreRepository utilisateurRepository;

    @Autowired
    private InscriptionService inscriptionService;

    @PostMapping("/api/inscription")
    public ResponseEntity<Inscription> createInscription(@RequestParam(value = "utilisateur_id") Long utilisateurId, @RequestParam(value = "activite_id") Long activiteId) {
        Inscription inscription = new Inscription();
        Activite activite = activiteRepository.findOne(activiteId);
        Utilisateur utilisateur = utilisateurRepository.findOne(utilisateurId);
        inscription.setActivite(activite);
        inscription.setParticipant(utilisateur);
        inscriptionService.saveInscription(inscription);
        return new ResponseEntity<Inscription>(inscription, HttpStatus.CREATED);
    }

    @GetMapping("api/inscription/{id}")
    public Inscription showInscription(@PathVariable Long id){
        Inscription ins = inscriptionService.findOneInscription(id);
        if (ins == null)
            throw new InscriptionNotFoundException(id);
        return ins;
    }

    @DeleteMapping(value = "/api/inscription/{id}")
    public void deleteInscription(@PathVariable("id") Long inscriptionId) {
        inscriptionService.deleteInscription(inscriptionId);
    }

    @GetMapping(value = "/api/inscription/search")
    public Page<Inscription> searchInscriptions(@RequestParam(value = "nom_utilisateur",required = false)String nomUtilisateur,
                                                @RequestParam(value = "titre_activite",required = false)String titreActivite,
                                                Pageable pageable) {
        if (nomUtilisateur == null && titreActivite == null)
            return inscriptionService.findAllInscription(pageable);
        return inscriptionService.findAll(nomUtilisateur, titreActivite, pageable);
    }

}
