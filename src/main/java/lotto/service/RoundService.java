package lotto.service;

import lotto.dao.RoundDAO;

import java.sql.SQLException;

public class RoundService {
    private RoundDAO roundDAO;

    public RoundService() {
        this.roundDAO = new RoundDAO();
    }

    public String searchRound() throws SQLException {
        return roundDAO.getRound();
    }
}
