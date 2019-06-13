package lotto.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoundDaoTest {
    private RoundDao roundDAO;
    @BeforeEach
    void setUp() {
        roundDAO = new RoundDao();
    }

    @Test
    void connection() {
        Connection connection = roundDAO.getConnection();
        assertNotNull(connection);
    }

    @Test
    void maxRound() throws SQLException{
        System.out.println(roundDAO.getMaxRound());
    }

    @Test
    void addRound() throws SQLException {
        int addedRound = roundDAO.addNextRound();
        System.out.println(addedRound + "추가");
    }
}
