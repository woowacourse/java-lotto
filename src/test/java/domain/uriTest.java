package domain;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class uriTest {
    @Test
    void _() throws URISyntaxException {
        URIBuilder ub = new URIBuilder("/numNonRandom");
        ub.addParameter("numTotalLottos", "3");

        System.out.println(ub.toString());
    }
}
