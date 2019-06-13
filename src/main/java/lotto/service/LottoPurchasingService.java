package lotto.service;

import lotto.dao.LottoDAO;
import lotto.dao.RoundDAO;
import lotto.domain.Lottos;

import java.sql.Connection;
import java.sql.SQLException;

public class LottoPurchasingService {

    public static Integer getNextRound(Connection connection) throws SQLException {
        RoundDAO roundDAO = new RoundDAO(connection);
        return roundDAO.getNextRound();
    }

    public static void loadDBRoundTable(Connection connection, Integer round) throws SQLException {
        RoundDAO roundDAO = new RoundDAO(connection);
        roundDAO.addRound(round.toString());
    }

    public static void loadDBLottoTable(Connection connection, Integer round, Lottos lottos) throws SQLException {
        LottoDAO lottoDAO = new LottoDAO(connection);
        lottoDAO.addLottos(round.toString(), lottos);
    }
}
