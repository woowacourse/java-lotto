package lotto.persistence;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDAOTest {
    private Connection con;
    private RoundDAO roundDao;

    @BeforeEach
    void setUp() {
        this.con = Connector.getConnection();
        this.roundDao = new RoundDAO(con);
    }

    @Test
    void addRoundTest() throws SQLException {
        int latestRoundIdBeforeAddRound = roundDao.getLatestRoundId();
        roundDao.addRound(100, 1.0);
        int latestRoundIdAfterAddRound = roundDao.getLatestRoundId();
        assertThat(latestRoundIdBeforeAddRound).isLessThan(latestRoundIdAfterAddRound);
        roundDao.removeRoundById(latestRoundIdAfterAddRound);
    }

    @Test
    void getPrizeTest() throws SQLException {
        roundDao.addRound(100, 1.0);
        int latestRoundId = roundDao.getLatestRoundId();
        assertThat(roundDao.getPrizeOfId(latestRoundId)).isEqualTo(100);
        roundDao.removeRoundById(latestRoundId);
    }

    @Test
    void getInterestRateTest() throws SQLException {
        roundDao.addRound(100, 1.0);
        int latestRoundId = roundDao.getLatestRoundId();
        assertThat(roundDao.getInterestRateOfId(latestRoundId)).isEqualTo(1.0, Offset.offset(0.01));
        roundDao.removeRoundById(latestRoundId);
    }

    @Test
    void removeTest() throws SQLException {
        int latestRoundId = roundDao.getLatestRoundId();
        roundDao.addRound(100, 1.0);
        roundDao.removeRoundById(roundDao.getLatestRoundId());
        assertThat(roundDao.getLatestRoundId()).isEqualTo(latestRoundId);
    }

    @AfterEach
    void tearDown() {
        Connector.closeConnection(con);
    }
}
