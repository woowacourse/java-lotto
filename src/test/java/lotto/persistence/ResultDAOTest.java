package lotto.persistence;

import lotto.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultDAOTest {
    private ResultDAO resultDao;
    private HashMap<Rank, Integer> result;
    private RoundDAO roundDao;

    @BeforeEach
    void setup() {
        this.roundDao = RoundDAO.getInstance();
        this.resultDao = ResultDAO.getInstance();
        this.result = new HashMap<>();
        result.put(Rank.FIRST, 1);
        result.put(Rank.SECOND, 0);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 0);
        result.put(Rank.FIFTH, 1);
        result.put(Rank.MISS, 2);
    }

    @Test
    void addResultTest() {
        roundDao.addRound(100, 1.0);
        int roundId = roundDao.getLatestRoundId();
        resultDao.addResult(roundId, result);
        assertThat(resultDao.findResultByRoundId(roundId)).isEqualTo(result);
        resultDao.removeResult(roundId);
        roundDao.removeRoundById(roundId);
    }
}
