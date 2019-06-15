package lotto.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LottoDAOTest {
    private LottoDAOConnector lottoDAO;
    private Connection con;

    @BeforeEach
    void setUp() {
        lottoDAO = new LottoDAOConnector();
    }

    @Test
    public void connection() {
        con = lottoDAO.getConnection();
        assertNotNull(con);
    }

    @AfterEach
    void tearDown() {
        lottoDAO.closeConnection(con);
        lottoDAO = null;
    }
}
