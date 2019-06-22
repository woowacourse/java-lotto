package lotto.dao;

import org.junit.jupiter.api.Test;

public class LottoStatusDaoTest {
    private LottoStatusDao lottoStatusDao = LottoStatusDao.getInstance();

    @Test
    void addResultInfo() {
        lottoStatusDao.addResultInfo(4, 2.0, "300000");
    }

    @Test
    void offerPrize() {
        System.out.println(lottoStatusDao.offerPrize(1));
    }

    @Test
    void offerReturnRate() {
        System.out.println(lottoStatusDao.offerReturnRate(1));
    }
}
