package lotto.service;

import lotto.dao.LottosDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.db.DatabaseConnection;
import lotto.domain.Lottos;
import lotto.domain.Price;
import lotto.domain.WinningLotto;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoService {
    static void addRoundInDB(int round, Price price) throws SQLException {
        Connection conn = new DatabaseConnection().getConnection();
        RoundDao roundDao = new RoundDao(conn);

        roundDao.addRound(round, price.getPrice());
    }

    static void addLottosInDB(int round, Lottos lottos) throws SQLException {
        Connection conn = new DatabaseConnection().getConnection();
        LottosDao lottosDao = new LottosDao(conn);

        lottosDao.addLottos(round, lottos);
    }

    static void addWinningLottoInDB(int round, WinningLotto winningLotto) throws SQLException {
        Connection conn = new DatabaseConnection().getConnection();
        WinningLottoDao winningLottoDao = new WinningLottoDao(conn);

        winningLottoDao.addWinningLotto(round, winningLotto);
    }

}
