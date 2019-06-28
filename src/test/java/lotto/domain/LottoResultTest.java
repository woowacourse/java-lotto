package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    public void 수익률_계산_테스트() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.add(Rank.FIFTH);
        lottoResult.add(Rank.FIFTH);

        assertThat(lottoResult.getRateOfReturn()).isEqualTo(500);
    }
}
