package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    public void createLottoResult() {
        LottoResult lottoResult = new LottoResult();
        assertThat(lottoResult).isInstanceOf(LottoResult.class);
    }

    @Test
    public void sumTotalPriceWithoutWinning() {
        LottoResult lottoResult = new LottoResult();
        long totalPrice = lottoResult.sumTotalPrice();
        assertThat(totalPrice).isEqualTo(0);
    }

    @Test
    public void putRankToLottoResult() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.putLottoRank(LottoRank.RANK_1);
        assertThat(lottoResult.getResultCount().get(LottoRank.RANK_1)).isEqualTo(1);
    }

    @Test
    public void sumTotalPriceTest() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.putLottoRank(LottoRank.RANK_1);
        assertThat(lottoResult.sumTotalPrice()).isEqualTo(2000000000);
    }
}
