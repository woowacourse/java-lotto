package lotto.service;

import lotto.dao.RoundDao;
import lotto.db.DatabaseConnection;
import lotto.domain.Price;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoundService {
    Connection conn = new DatabaseConnection().getConnection();
    RoundDao roundDao = new RoundDao(conn);

    public int getPresentRound() throws SQLException {
        return roundDao.findLatestRound();
    }

    public List<Integer> getAllRound() throws SQLException{
        return roundDao.findAllRound();
    }

    public Price getPrice(String price) {
        return new Price(Integer.parseInt(price));
    }

    public void addRoundInDB(int round, Price price) throws SQLException {
        roundDao.addRound(round, price.getPrice());
    }
}
