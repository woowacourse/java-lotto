package lotto.persistence;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectorTest {
    @Test
    void connectionTest() {
        Connection con = Connector.getConnection();
        assertThat(con).isNotNull();
        Connector.closeConnection(con);
    }


}
