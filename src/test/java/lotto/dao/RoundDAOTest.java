package lotto.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDAOTest {
    final static int AMOUNT_TEST = 10_000;

    private RoundDAO roundDAO;

    @BeforeEach
    void setUp() {
        roundDAO = new RoundDAO();
    }

    @Test
    public void findMaxRound() throws SQLException {
        assertThat(roundDAO.findMaxRound()).isEqualTo(0);
    }

    @Test
    public void findAmountByRound() throws SQLException {
        roundDAO.addRound(AMOUNT_TEST);
        int amount = roundDAO.findAmountByRound(roundDAO.findMaxRound());
        assertThat(amount).isEqualTo(AMOUNT_TEST);
        removeRound();
    }

    @Test
    public void addRound() throws SQLException {
        roundDAO.addRound(AMOUNT_TEST);
        removeRound();
    }

    @Test
    public void removeRound() throws SQLException {
        roundDAO.removeRound(roundDAO.findMaxRound());
    }

    @AfterEach
    public void tearDown() {
        roundDAO = null;
    }
}
