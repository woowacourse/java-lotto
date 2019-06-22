package lotto.service;

import lotto.dao.RoundDao;
import lotto.db.DatabaseConnection;
import lotto.domain.Price;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundService {
    private Connection conn = new DatabaseConnection().getConnection();
    private RoundDao roundDao = new RoundDao(conn);

    public int getPresentRound() throws SQLException {
        return roundDao.findLatestRound() + 1;
    }

    public List<Integer> getAllRound() throws SQLException {
        List<Integer> rounds = new ArrayList<>();
        int countOfRounds = roundDao.findLatestRound();
        for (int i = 1; i <= countOfRounds; i++) {
            rounds.add(i);
        }
        return rounds;
    }

    public Price getPrice(String price) {
        return new Price(Integer.parseInt(price));
    }

    public void addRoundInDB(int round, Price price) throws SQLException {
        roundDao.addRound(round, price.getPrice());
    }
}
