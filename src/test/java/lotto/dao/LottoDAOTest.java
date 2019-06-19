package lotto.dao;

import lotto.domain.creator.LottoCreator;
import lotto.domain.creator.ManualLottoCreator;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoDAOTest {
    private LottoDAO lottoDAO;

    @BeforeEach
    void setUp() {
        this.lottoDAO = LottoDAOImpl.getInstance();
    }

    @Test
    void crdLotto() throws Exception {
        LottoGameDAO lottoGameDAO = LottoGameDAOImpl.getInstance();
        lottoGameDAO.addLottoGame(0);

        LottoCreator creator1 = new ManualLottoCreator(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto1 = creator1.createLotto();
        assertEquals(lottoDAO.addLotto(lotto1, 0), 1);

        LottoCreator creator2 = new ManualLottoCreator(Arrays.asList(2, 3, 4, 5, 6, 7));
        Lotto lotto2 = creator2.createLotto();
        lottoDAO.addLotto(lotto2, 0);
        assertTrue(lottoDAO.findByRound(0).containsAll(Arrays.asList(lotto1, lotto2)));

        assertEquals(lottoDAO.deleteLotto(0), 2);

        lottoGameDAO.deleteLottoGame(0);
    }
}