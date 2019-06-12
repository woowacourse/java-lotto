package lotto.service;

import lotto.dao.RoundDAO;

import java.sql.SQLException;
import java.util.List;

public class RoundService {
    private final RoundDAO roundDAO = new RoundDAO();

    public void addRound(int amount) throws SQLException {
        roundDAO.addRound(amount);
    }

    public int findMaxRound() throws SQLException {
        return roundDAO.findMaxRound();
    }

    public List<Integer> findAllRounds() throws SQLException {
        return roundDAO.findAllRounds();
    }
}
