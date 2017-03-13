package friendsofmine;

/**
 * Created by QYL on 2017/3/9.
 */
import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.service.ActiviteService;
import friendsofmine.service.InitialisationService;
import friendsofmine.service.UtilisateurService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapTest {

    @Autowired
    private Bootstrap bootstrap;

    @Autowired
    ActiviteService activiteService;

    @Autowired
    UtilisateurService utilisateurService;

    private Utilisateur thom, mary;
    private Activite randonnee, lindyhop, taekwondo;

    @Before
    public void setUp() {
        InitialisationService initialisationService = bootstrap.getInitialisationService();
        thom = initialisationService.getThom();
        mary = initialisationService.getMary();
        randonnee = initialisationService.getRandonnee();
        lindyhop = initialisationService.getLindyhop();
        taekwondo = initialisationService.getTaekwondo();
    }

    @Test
    public void testNombreActivite() {
        assertEquals(3, activiteService.findAllActivite().size());
    }

    @Test
    public void testNombreUtilisateur() {
        assertEquals(2, utilisateurService.findAllUtilisateur().size());
    }

    @Test
    public void testActiviteTriParTitre() {
        ArrayList<Activite> activites = activiteService.findAllActivite();
        assertEquals(lindyhop.getTitre(), activites.get(0).getTitre());
        assertEquals(randonnee.getTitre(), activites.get(1).getTitre());
        assertEquals(taekwondo.getTitre(), activites.get(2).getTitre());
    }

    @Test
    public void testUtilisateursTriParNom() {
        ArrayList<Utilisateur> utilisateurs = utilisateurService.findAllUtilisateur();
        assertEquals(mary.getFirstName(), utilisateurs.get(0).getFirstName());
        assertEquals(thom.getFirstName(), utilisateurs.get(1).getFirstName());
    }

    @Test
    @Transactional
    public void testNombreDActivitesParUtilisateur() {
        ArrayList<Utilisateur> utilisateurs = utilisateurService.findAllUtilisateur();
        assertEquals(1, utilisateurs.get(0).getActivites().size());
        assertEquals(2, utilisateurs.get(1).getActivites().size());
    }

}