package lotto.dao;

import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;

    @BeforeEach
    void setUp() {
        this.winningLottoDAO = WinningLottoDAOImpl.getInstance();
    }

    @Test
    void crdWinningLotto() {
        LottoGameDAO lottoGameDAO = LottoGameDAOImpl.getInstance();
        lottoGameDAO.addLottoGame(0);

        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        assertEquals(winningLottoDAO.addWinningLotto(winningLotto, 0), 1);
        assertEquals(winningLottoDAO.findByRound(0), winningLotto);
        assertEquals(winningLottoDAO.deleteWinningLotto(0), 1);

        lottoGameDAO.deleteLottoGame(0);
    }
}