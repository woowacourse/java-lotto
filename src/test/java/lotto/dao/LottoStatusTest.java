package lotto.dao;

import org.junit.jupiter.api.Test;

public class LottoStatusTest {
    private LottoStatus lottoStatus = LottoStatus.getInstance();

    @Test
    void addResultInfo() {
        lottoStatus.addResultInfo(4, 2.0, "300000");
    }

    @Test
    void offerPrize() {
        System.out.println(lottoStatus.offerPrize(1));
    }

    @Test
    void offerReturnRate() {
        System.out.println(lottoStatus.offerReturnRate(1));
    }
}
