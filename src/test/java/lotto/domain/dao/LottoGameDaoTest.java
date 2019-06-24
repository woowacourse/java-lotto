package lotto.domain.dao;

import lotto.domain.user.PurchaseAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class LottoGameDaoTest {
    private LottoGameDao lottoGameDao;
    private PurchaseAmount purchaseAmount;

    @BeforeEach
    void setUp() {
        lottoGameDao = LottoGameDao.getInstance();
        purchaseAmount = new PurchaseAmount(10000);
    }

    @Test
    void LOTTO_개수가_잘_들어가는_지() throws SQLException {
        lottoGameDao.addLottoAmount(purchaseAmount);
    }
}