package lotto.service;

import lotto.database.ConnectionUtil;
import lotto.database.dao.RoundDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class RoundService {
    public static int getMaxRound() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        return new RoundDAO(connection).getMaxRound();
    }

    public static void createRound(final int nowRound) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        new RoundDAO(connection).addRound(nowRound);
    }
}
