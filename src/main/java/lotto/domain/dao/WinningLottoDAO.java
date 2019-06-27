package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.WinningLotto;

public interface WinningLottoDAO {

    static WinningLottoDAOImpl getInstance() {
        return WinningLottoDAOImpl.getInstance();
    }

    void addWinningLotto(WinningLottoDTO winningLottoDTO);

    int getLatestRound();

    WinningLotto getWinningLotto(int round);

    void deleteWinningLotto(final int round);

}
