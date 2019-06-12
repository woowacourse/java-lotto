package lotto.database;

import lotto.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResultDAOTest {
    private Connection con;
    private ResultDAO resultDao;
    private HashMap<Rank, Integer> result;

    @BeforeEach
    void setup() {
        this.con = Connector.getConnection();
        this.resultDao = new ResultDAO(con);
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
        Map<Rank, Integer> result = new HashMap<>();
        result.put(Rank.FIRST, 1);
        result.put(Rank.SECOND, 0);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 0);
        result.put(Rank.FIFTH, 1);
        result.put(Rank.MISS, 2);
        resultDao.addResult(result);
    }

    /*
    @Test
    void findResultTest() throws SQLException {
        resultDao.addResult(result);
        assertThat(resultDao.findResultById(1)).isEqualTo(result);
    }

    @Test
    void removeResultTest() throws SQLException {
        resultDao.addResult(result);
        resultDao.removeResult(7);
    }
    */
}
