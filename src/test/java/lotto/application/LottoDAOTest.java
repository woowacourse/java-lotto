package lotto.application;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LottoDAOTest {
    @Test
    void connection() {
        Connection con = LottoDAO.getConnection();
        assertNotNull(con);
    }
}