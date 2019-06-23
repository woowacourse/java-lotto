package lotto.persistence;

import lotto.domain.Rank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultDAOTest {
    private Connection con;
    private ResultDAOImpl resultDao;
    private HashMap<Rank, Integer> result;
    private RoundDAOImpl roundDao;

    @BeforeEach
    void setup() {
        this.con = Connector.getConnection();
        this.roundDao = new RoundDAOImpl(con);
        this.resultDao = new ResultDAOImpl(con);
        this.result = new HashMap<>();
        result.put(Rank.FIRST, 1);
        result.put(Rank.SECOND, 0);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 0);
        result.put(Rank.FIFTH, 1);
        result.put(Rank.MISS, 2);
    }

    @Test
    void addResultTest() throws SQLException {
        roundDao.addRound(100, 1.0);
        int roundId = roundDao.getLatestRoundId();
        resultDao.addResult(roundId, result);
        assertThat(resultDao.findResultByRoundId(roundId)).isEqualTo(result);
        resultDao.removeResult(roundId);
        roundDao.removeRoundById(roundId);
    }

    @AfterEach
    void tearDown() {
        Connector.closeConnection(con);
    }
}
