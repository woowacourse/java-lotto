package lotto.service;

import lotto.dao.*;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

import java.sql.Connection;
import java.sql.SQLException;

public class DBAccessor {

    public static Lottos getDBLottos(Connection connection, String lottoRound) throws SQLException {
        LottoDAO lottoDAO = new LottoDAO(connection);
        return lottoDAO.findByLottoRound(lottoRound);
    }

    public static WinningLotto getDBWinningLotto(Connection connection, String lottoRound) throws SQLException {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO(connection);
        return winningLottoDAO.findByLottoRound(lottoRound);
    }

    public static LottoResultDTO getDBLottoResultDTO(Connection connection, String lottoRound) throws SQLException {
        LottoResultDAO lottoResultDAO = new LottoResultDAO(connection);
        return lottoResultDAO.findByLottoRound(lottoRound);
    }

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
