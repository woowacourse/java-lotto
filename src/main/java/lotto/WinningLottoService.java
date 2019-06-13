package lotto;

import lotto.dao.TurnDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.WinningLotto;

public class WinningLottoService {
    private static final WinningLottoService INSTANCE = new WinningLottoService();

    private final WinningLottoDao winningLottoDao;
    private final TurnDao turnDao;

    private WinningLottoService() {
        winningLottoDao = WinningLottoDao.getInstance();
        turnDao = TurnDao.getInstance();
    }

    public static WinningLottoService getInstance() {
        return INSTANCE;
    }

    public void add(final WinningLotto winningLotto) {
        int nextTurn = turnDao.findNext();
        winningLottoDao.add(winningLotto, nextTurn);
    }
}
