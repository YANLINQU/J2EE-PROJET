package friendsofmine;

/**
 * Created by QYL on 2017/3/21.
 */
import friendsofmine.domain.Inscription;
import friendsofmine.service.InscriptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EqualsInORMTest {

    @Autowired
    private Bootstrap bootstrap;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InscriptionService inscriptionService;

    @LocalServerPort
    private int port;

    @Test
    public void testEgaliteDInscriptionPersisteeViaId() throws Exception {
        Inscription maryOnTaekwondo = bootstrap.getInitialisationService().getMaryOnTaekwondo();
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<String, Long>();
        map.add("utilisateur_id", maryOnTaekwondo.getParticipant().getId());
        map.add("activite_id", maryOnTaekwondo.getActivite().getId());

        Inscription inscription = restTemplate.postForObject("http://localhost:" + port + "/api/inscription",map, Inscription.class);

        assertEquals(maryOnTaekwondo.getParticipant().getId(), inscription.getParticipant().getId());
    }

    @Test
    public void testEgaliteDInscriptionPersisteeViaEquals() throws Exception {
        Inscription maryOnTaekwondo = bootstrap.getInitialisationService().getMaryOnTaekwondo();
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<String, Long>();
        map.add("utilisateur_id", maryOnTaekwondo.getParticipant().getId());
        map.add("activite_id", maryOnTaekwondo.getActivite().getId());

        Inscription inscription = restTemplate.postForObject("http://localhost:" + port + "/api/inscription",map, Inscription.class);

        assertEquals(maryOnTaekwondo.getParticipant(), inscription.getParticipant());
    }

}