package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LottoGameDAOTest {
    private LottoGameDAO lottoGameDAO;

    @BeforeEach
    void setup() {
        this.lottoGameDAO = LottoGameDAOImpl.getInstance();
    }

    @Test
    void crdLottoGame() throws Exception {
        assertEquals(lottoGameDAO.addLottoGame(0), 1);
        //assertEquals(lottoGameDAO.getLastRound(), 0);
        assertEquals(lottoGameDAO.deleteLottoGame(0), 1);
    }
}