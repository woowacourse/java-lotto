package lotto;

import domain.LottoRank;
import domain.LottoResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 로또_결과의_초기값_확인() {
        LottoResult lottoResult = new LottoResult();
        assertThat(lottoResult.getSize()).isEqualTo(5);

        for (LottoRank rank : LottoRank.values()) {
            assertThat(lottoResult.getCount(rank)).isEqualTo(0);
        }
    }

    @Test
    void 총_수익_계산_테스트() {
        int lottoCount = 4;
        LottoResult lottoResult = new LottoResult();
        lottoResult.addWinningRankCount(LottoRank.THIRD);
        lottoResult.addWinningRankCount(LottoRank.FOURTH);
        lottoResult.addWinningRankCount(LottoRank.FIFTH);
        lottoResult.addWinningRankCount(LottoRank.FIFTH);

        int profitRatio = lottoResult.calculateProfitRatio(4);
        assertThat(profitRatio).isEqualTo(390);
    }
}
