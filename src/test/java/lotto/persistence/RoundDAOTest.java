package lotto.persistence;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDAOTest {
    private RoundDAO roundDao;

    @BeforeEach
    void setUp() {
        this.roundDao = RoundDAO.getInstance();
    }

    @Test
    void addRoundTest() {
        int latestRoundIdBeforeAddRound = roundDao.getLatestRoundId();
        roundDao.addRound(100, 1.0);
        int latestRoundIdAfterAddRound = roundDao.getLatestRoundId();
        assertThat(latestRoundIdBeforeAddRound).isLessThan(latestRoundIdAfterAddRound);
        roundDao.removeRoundById(latestRoundIdAfterAddRound);
    }

    @Test
    void getPrizeTest() {
        roundDao.addRound(100, 1.0);
        int latestRoundId = roundDao.getLatestRoundId();
        assertThat(roundDao.getPrizeOfId(latestRoundId)).isEqualTo(100);
        roundDao.removeRoundById(latestRoundId);
    }

    @Test
    void getInterestRateTest() {
        roundDao.addRound(100, 1.0);
        int latestRoundId = roundDao.getLatestRoundId();
        assertThat(roundDao.getInterestRateOfId(latestRoundId)).isEqualTo(1.0, Offset.offset(0.01));
        roundDao.removeRoundById(latestRoundId);
    }

    @Test
    void removeTest() {
        int latestRoundId = roundDao.getLatestRoundId();
        roundDao.addRound(100, 1.0);
        roundDao.removeRoundById(roundDao.getLatestRoundId());
        assertThat(roundDao.getLatestRoundId()).isEqualTo(latestRoundId);
    }
}
