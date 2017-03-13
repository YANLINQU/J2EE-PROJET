package friendsofmine.controllers;

import friendsofmine.Bootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by QYL on 2017/2/27.
 */
@Controller
public class ActiviteController {
    @Autowired
    private Bootstrap bootstrap;
    @RequestMapping(value = "/activites", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("activite",this.bootstrap.getAllActivite());
        return "activites";
    }
}
