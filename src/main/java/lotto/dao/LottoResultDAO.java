package lotto.dao;

import lotto.domain.LottosResult;

import java.sql.SQLException;

public interface LottoResultDAO {
    long findByRound(int round) throws SQLException;

    int addLottoResult(LottosResult result, int round) throws SQLException;

    int deleteLottoResult(int round) throws SQLException;
}
