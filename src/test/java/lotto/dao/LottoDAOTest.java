package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LottoDAOTest {
    private LottoDAO lottoDAO;

    @BeforeEach
    public void setup() {
        lottoDAO = new LottoDAO();
    }

    @Test
    public void connection() {
        Connection con = lottoDAO.getConnection();
        assertNotNull(con);
    }
}
