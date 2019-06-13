package lotto.service;

import lotto.dao.*;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

import java.sql.Connection;
import java.sql.SQLException;

public class DBGetter {

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

    public static Integer getCurrentRound(Connection connection) throws SQLException {
        RoundDAO roundDAO = new RoundDAO(connection);
        return roundDAO.getCurrentRound();
    }

    public static Integer getNextRound(Connection connection) throws SQLException {
        RoundDAO roundDAO = new RoundDAO(connection);
        return roundDAO.getNextRound();
    }
}
