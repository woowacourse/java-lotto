package lotto.dao;

import java.sql.SQLException;

public interface LottoGameDAO {
    int getLastRound() throws SQLException;

    int addLottoGame(int round) throws SQLException;

    int deleteLottoGame(int round) throws SQLException;
}
