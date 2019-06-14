package lotto.domain.lotto.db;

import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.db.dao.WinningLottoDAO;

import java.sql.SQLException;

public class WinningLottoDBhandler implements DBHandler<WinningLotto> {
    @Override
    public void add(WinningLotto winningLotto) throws SQLException {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO
                (new ConnectionHandler("lottoGame"));
        winningLottoDAO.create();
        winningLottoDAO.add(winningLotto);
        winningLottoDAO.closeConnection();
    }

    @Override
    public WinningLotto foundById(int id) throws SQLException {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO
                (new ConnectionHandler("lottoGame"));
        winningLottoDAO.create();
        WinningLotto winningLotto = winningLottoDAO.foundById(id);
        winningLottoDAO.closeConnection();

        return winningLotto;
    }

    public int getRecentId() throws SQLException {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO
                (new ConnectionHandler("lottoGame"));
        winningLottoDAO.create();
        int recentId = winningLottoDAO.getRecentId();
        winningLottoDAO.closeConnection();
        return recentId;
    }
}
