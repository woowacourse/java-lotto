package lotto.domain.dao;

import lotto.domain.model.Money;
import lotto.domain.model.Rank;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrizeResultDAOTest {
    private PrizeResultDAO prizeResultDAO;

    @Before
    public void setUp() {
        prizeResultDAO = new PrizeResultDAO();
    }

    @Test
    public void addPrizeResult() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIRST);
        ranks.add(Rank.SECOND);
        int round = 2;
        Money money = new Money(3000);
        try {
            prizeResultDAO.addPrizeResult(ranks, round, money);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
