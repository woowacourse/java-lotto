package lotto.domain.utils;

import org.junit.Test;

import java.sql.Connection;

import static junit.framework.TestCase.assertNotNull;

public class ConnectionGeneratorTest {

    @Test
    public void connection() {
        Connection con = ConnectionGenerator.getConnection();
        assertNotNull(con);
    }
}
