package friendsofmine;

import friendsofmine.controllers.UtilisateurController;
import friendsofmine.domain.Utilisateur;
import friendsofmine.service.ActiviteService;
import friendsofmine.service.UtilisateurService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.ui.Model;
import static org.junit.matchers.JUnitMatchers.hasItem;


/**
 * Created by QYL on 2017/2/27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ActiviteService activiteService;

    private Utilisateur util, utilNonSauve;

    @Before
    public void setup() {
        util = new Utilisateur("Doe", "John", "john@doe.com", "M");
        utilisateurService.saveUtilisateur(util);
        utilNonSauve = new Utilisateur("Morrissey", "Steven Patrick", "momo@rrissey.co.uk", "M");
    }

    @Test
    public void testGetUtilisateurs() throws Exception{
        mockMvc.perform(get("/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurs"))
                .andExpect(content().string(Matchers.containsString("<h2>Liste des Utilisateurs</h2>")))
                .andDo(print());
    }

    @Test
    public void testReadUtilisateurIdValide() throws Exception{
        mockMvc.perform(get("/utilisateur/" + util.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurShow"))
                .andExpect(content().string(Matchers.containsString(util.getFirstName())))
                .andExpect(content().string(Matchers.containsString(util.getSecondeName())))
                .andExpect(content().string(Matchers.containsString(util.getAdresse())))
                .andDo(print());
    }

    @Test
    public void testReadUtilisateurIdInvalide() throws Exception{
        mockMvc.perform(get("/utilisateur/" + (util.getId() + 1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("error"))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateur() throws Exception{
        mockMvc.perform(get("/utilisateur/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateurValide() throws Exception{
        mockMvc.perform(post("/utilisateur")
                .param("firstName", utilNonSauve.getFirstName())
                .param("secondeName", utilNonSauve.getSecondeName())
                .param("adresse", utilNonSauve.getAdresse())
                .param("sex", utilNonSauve.getSex()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("/utilisateur/*"))
                .andDo(print());
    }
/*
    @Test
    public void testCreateUtilisateurNomInvalide() throws Exception{
        mockMvc.perform(post("/utilisateur")
                .param("firstName", "")
                .param("secondeName", utilNonSauve.getSecondeName())
                .param("adresse", utilNonSauve.getAdresse())
                .param("sex", utilNonSauve.getSex()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString("size must be between 1 and 16")))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateurPrenomInvalide() throws Exception{
        mockMvc.perform(post("/utilisateur")
                .param("nom", utilNonSauve.getFirstName())
                .param("prenom", "")
                .param("email", utilNonSauve.getAdresse())
                .param("sexe", utilNonSauve.getSex()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString("size must be between 1 and")))
                .andDo(print());
    }

    @Test
    public void testCreateUtilisateurEmailInvalide() throws Exception{
        mockMvc.perform(post("/utilisateur")
                .param("nom", utilNonSauve.getFirstName())
                .param("prenom", utilNonSauve.getSecondeName())
                .param("email", "toto")
                .param("sexe", utilNonSauve.getSex()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString("not a well-formed email address")))
                .andDo(print());
    }

    @Test
    public void testEditUtilisateurIdValide() throws Exception{
        Long savId = util.getId();
        mockMvc.perform(get("/utilisateur/edit/" + savId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("utilisateurForm"))
                .andExpect(content().string(Matchers.containsString(util.getFirstName())))
                .andExpect(content().string(Matchers.containsString(util.getSecondeName())))
                .andExpect(content().string(Matchers.containsString(util.getAdresse())))
                .andExpect(model().attribute("utilisateur", hasProperty("id", (Matcher<?>) is(savId))))
                .andDo(print());
    }
*/
    @Test
    public void testEditUtilisateurIdInvalide() throws Exception{
        mockMvc.perform(get("/utilisateur/edit/" + (util.getId() + 1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("error"))
                .andDo(print());
    }

}

