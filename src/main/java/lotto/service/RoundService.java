package lotto.service;

import lotto.dao.RoundDao;

import java.sql.SQLException;

public class RoundService {
    private RoundDao roundDAO;

    public RoundService() {
        this.roundDAO = new RoundDao();
    }

    public String searchRound() throws SQLException {
        return roundDAO.getRound();
    }
}
