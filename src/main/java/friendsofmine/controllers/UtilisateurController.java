package friendsofmine.controllers;

import friendsofmine.domain.Utilisateur;
import friendsofmine.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping(value = "/utilisateurs")
    public String list(Model model) {
        model.addAttribute("utilisateurs", utilisateurService.findAllUtilisateurs());
        return "utilisateurs";
    }

    @GetMapping("utilisateur/{id}")
    public String showUtilisateur(@PathVariable Long id, Model model){
        Utilisateur util = utilisateurService.findOneUtilisateur(id);
        if (util == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }
        model.addAttribute("utilisateur", util);
        return "utilisateurShow";
    }

    @RequestMapping("/utilisateur/new")
    public String createUtilisateur(Model model){
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateurForm";
    }

    @PostMapping(value = "/utilisateur")
    public String createOrUpdateUtilisateur(@Valid Utilisateur util,
                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "utilisateurForm";
        }
        utilisateurService.saveUtilisateur(util);
        return "redirect:/utilisateur/" + util.getId();
    }

    @GetMapping("utilisateur/edit/{id}")
    public String editUtilisateur(@PathVariable Long id, Model model){
        Utilisateur util = utilisateurService.findOneUtilisateur(id);
        if (util == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }
        model.addAttribute("utilisateur", util);
        return "utilisateurForm";
    }

    @DeleteMapping("utilisateur/delete/{id}")
    public String deleteUtilisateur(@PathVariable Long id, Model model){
        Utilisateur util = utilisateurService.findOneUtilisateur(id);
        if (util == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }
        if (util.getActivites().size() != 0) {
            model.addAttribute("customMessage", "Impossible. L'utilisateur est responsable d'activités. \n " +
                    "Un nouveau responsable doit être désigné avant de supprimer " +
                    util.getPrenom() + " " + util.getNom() + ".");
            return "error";
        }
        utilisateurService.delUtilisateur(id);
        return "redirect:/utilisateurs";
    }

    @GetMapping("utilisateur/search")
    public String searchUtilisateurs(@RequestParam(value = "sexe",required = true)String sexe, Model model) {
        List<Utilisateur> util;
        if (sexe.equals("M"))
            util = utilisateurService.findUtilisateursM();
        else if (sexe.equals("F"))
            util = utilisateurService.findUtilisateursF();
        else
            util = utilisateurService.findAllUtilisateurs();
        model.addAttribute("utilisateurs", util);
        return "utilisateurs";
    }

}
