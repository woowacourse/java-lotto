package lotto.domain.dao;

import lotto.domain.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoundDaoTest {
    private RoundDao roundDAO;
    private DataSource dataSource = DBUtil.getDataSource();

    @BeforeEach
    void setUp() {
        roundDAO = new RoundDao(dataSource);
    }

    @Test
    void connection() throws SQLException {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
    }

    @Test
    void maxRound() throws SQLException {
        System.out.println(roundDAO.getMaxRound());
    }

    @Test
    void addRound() throws SQLException {
        int addedRound = roundDAO.addNextRound();
        System.out.println(addedRound + "추가");
    }
}
