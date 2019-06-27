package lotto.domain.utils;

import lotto.domain.dao.Connector;
import lotto.domain.dao.JDBCConnectException;
import lotto.domain.dao.JDBCDriverLoadException;
import org.junit.Test;

import java.sql.Connection;

import static junit.framework.TestCase.assertNotNull;

public class ConnectorTest {

    @Test
    public void connection() throws JDBCDriverLoadException, JDBCConnectException {
        Connection con = Connector.getConnection();
        assertNotNull(con);
    }
}
