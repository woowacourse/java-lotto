package lotto.service;

import lotto.database.dao.RoundDAO;

import java.sql.SQLException;

public class RoundService {
    private static RoundDAO roundDAO = RoundDAO.getInstance();

    public static int getMaxRound() throws SQLException {
        return roundDAO.getMaxRound();
    }

    public static void createRound(final int nowRound) throws SQLException {
        roundDAO.addRound(nowRound);
    }
}
