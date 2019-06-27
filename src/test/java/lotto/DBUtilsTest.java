package lotto;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBUtilsTest {

    @Test
    public void connection() {
        Connection con = DBUtils.getConnection();
        assertNotNull(con);
    }
}