package lotto.dao;

import lotto.domain.lotto.Lotto;

import java.sql.SQLException;
import java.util.List;

public interface LottoDAO {
    List<Lotto> findByRound(int round) throws SQLException;

    int addLotto(Lotto lotto, int round) throws SQLException;

    int deleteLotto(int round) throws SQLException;
}
