package lotto.service;

import lotto.dao.LottoResultDAO;
import lotto.dao.WinningLottoDAO;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;

import java.sql.Connection;
import java.sql.SQLException;

public class LottoResultService {

    public static void loadDBWinningLottoTable(Connection connection, Integer round, WinningLotto winningLotto)
            throws SQLException {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO(connection);
        winningLottoDAO.addWinningLotto(round.toString(), winningLotto);
    }

    public static void loadDBLottoResultTable(Connection connection, Integer round, LottoResult lottoResult)
            throws SQLException {
        LottoResultDAO lottoResultDAO = new LottoResultDAO(connection);
        lottoResultDAO.addLottoResult(round.toString(), lottoResult);
    }
}
