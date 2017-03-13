package friendsofmine.controllers;

import friendsofmine.Bootstrap;
import friendsofmine.domain.Utilisateur;
import friendsofmine.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by QYL on 2017/2/27.
 */
@Controller
public class UtilisateurController {
    @Autowired
    private Bootstrap bootstrap;
    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping(value = "/utilisateurs", method = RequestMethod.GET)
    public String index(Model model){
        //model.addAttribute("utilisateurs",this.bootstrap.getAllActivite());
        model.addAttribute("utilisateurs",this.bootstrap.getAllUtilisateur());
        return "utilisateurs";
    }

    @RequestMapping("/utilisateur/{id}")
    public String utilisateursId(@PathVariable Long id, Model model){
        int len=this.bootstrap.getAllUtilisateur().size();
        if(len >= id){
            Utilisateur util=this.bootstrap.getUtilisateurServices().findOneUtilisateur(id);
            model.addAttribute("utilisateur",util);
            model.addAttribute("activites_utilisateur",util.getActivites());
            return "utilisateurShow";
        }else{
            return "error";
        }
    }

    @RequestMapping("/utilisateur/new")
    public String utilisateursNewPage(Model model){
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateurForm";
    }

    @RequestMapping("/utilisateur")
    public String utilisateurCreate(@Valid Long id,@Valid String firstName,@Valid String secondeName,@Valid String adresse,@Valid String sex){
        Utilisateur util;
        System.out.println("++++"+id+"+++++");
        int idd=1;
        try{
            id.equals("");
        }catch (Exception e){
            idd=0;
        }
        if(idd==0){
            util = new Utilisateur(firstName,secondeName,adresse,sex);
            this.utilisateurService.saveUtilisateur(util);
        }else{
            System.out.println(firstName);
            util = this.utilisateurService.findOneUtilisateur(id);
            util.setFirstName(firstName);
            util.setSecondeName(secondeName);
            util.setAdresse(adresse);
            util.setSex(sex);
            this.utilisateurService.saveUtilisateur(util);
        }
        return "redirect:/utilisateur/" + util.getId();
    }

    @RequestMapping("/utilisateur/edit/{id}")
    public String utilisateurEdit(@PathVariable Long id,Model model){
        Utilisateur util = this.utilisateurService.findOneUtilisateur(id);
        model.addAttribute("utilisateur", util);
        return "utilisateurForm";
    }
    /*
    @RequestMapping("/utilisateur/delete/{id}")
    public String utilisateurDele(@PathVariable Long id,Model model){
        Utilisateur util = this.utilisateurService.findOneUtilisateur(id);
        util.delActivites();
        this.utilisateurService.saveUtilisateur(util);
        this.utilisateurService.delUtilisateur(id);
        return "redirect:/utilisateurs";
    }
    */
}
