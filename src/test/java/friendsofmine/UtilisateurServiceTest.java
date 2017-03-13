package friendsofmine;

/**
 * Created by QYL on 2017/3/6.
 */
import friendsofmine.domain.Activite;
import friendsofmine.domain.Utilisateur;
import friendsofmine.service.UtilisateurService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilisateurServiceTest {

    @Autowired
    private UtilisateurService utilisateurService;


    private Utilisateur util, util1;

    @Before
    public void setup() {
        util = new Utilisateur("nom", "prenom", "toto@toto.fr", "F");
        util1 = new Utilisateur("nom1", "prenom1", "toto@toto.fr", "F");
        utilisateurService.saveUtilisateur(util1);
    }

    @Test
    public void testSaveUtilisateur(){
        assertNull(util.getId());
        utilisateurService.saveUtilisateur(util);
        assertNotNull(util.getId());
    }

    @Test
    public void testUtilisateurRecupereeNotNull() {
        utilisateurService.saveUtilisateur(util);
        Utilisateur fetched = utilisateurService.findOneUtilisateur(util.getId());
        assertNotNull(fetched);
    }

    @Test
    public void testUtilisateurRecupereeInchangee() {
        Utilisateur fetched = utilisateurService.findOneUtilisateur(util1.getId());
        assertEquals(fetched.getId(), util1.getId());
        assertEquals(fetched.getAdresse(), util1.getAdresse());
    }

    @Test
    public void testUtilisateurMiseAJour() {
        Utilisateur fetched = utilisateurService.findOneUtilisateur(util1.getId());
        fetched.setAdresse("tutu@tutu.fr");
        utilisateurService.saveUtilisateur(fetched);
        Utilisateur fetchedUpdated = utilisateurService.findOneUtilisateur(fetched.getId());
        assertEquals(fetched.getAdresse(), fetchedUpdated.getAdresse());
    }

    @Test
    public void testNombreDUtilisateurPersisteeApresMiseAJour() {
        long count = utilisateurService.countUtilisateur();
        Utilisateur fetched = utilisateurService.findOneUtilisateur(util1.getId());
        fetched.setAdresse("titi@titi.fr");
        utilisateurService.saveUtilisateur(fetched);
        assertEquals(count,utilisateurService.countUtilisateur());
    }

    @Test
    public void testTypeRepository() {
        assertThat(utilisateurService.getUtilisateurRepository(), instanceOf(PagingAndSortingRepository.class));
    }

}