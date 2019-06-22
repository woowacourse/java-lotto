package lotto.service;

import lotto.dao.WinningLottoDao;
import lotto.db.DatabaseConnection;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.utils.Converter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class WinningLottoService {
    private Connection conn = new DatabaseConnection().getConnection();
    private WinningLottoDao winningLottoDao = new WinningLottoDao(conn);

    public WinningLotto getWinningLotto(String winningLotto, String bonus) {
        Lotto lotto = new Lotto(Converter.convertNumbers(winningLotto));
        LottoNumber bonusNo = LottoNumber.valueOf(Integer.parseInt(bonus));

        return new WinningLotto(lotto, bonusNo);
    }

    public void addWinningLottoInDB(int round, WinningLotto winningLotto) throws SQLException {
        winningLottoDao.addWinningLotto(round, winningLotto);
    }

    public WinningLotto getWinningLottoByRound(int round) throws SQLException {
        return winningLottoDao.findWinningLottoByRound(round);
    }
}
