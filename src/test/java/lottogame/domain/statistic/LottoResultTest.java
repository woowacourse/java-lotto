package lottogame.domain.statistic;

import lottogame.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    @DisplayName("로또 당첨 개수에 맞는 당첨 결과가 생성 되는 지 확인")
    @Test
    void 로또_결과_생성_테스트() {
        LottoResult lottoResult = new LottoResult(3, false);
        assertThat(lottoResult.equals(Rank.FIFTH)).isTrue();

        lottoResult = new LottoResult(4, false);
        assertThat(lottoResult.equals(Rank.FOURTH)).isTrue();

        lottoResult = new LottoResult(5, false);
        assertThat(lottoResult.equals(Rank.THIRD)).isTrue();

        lottoResult = new LottoResult(5, true);
        assertThat(lottoResult.equals(Rank.SECOND)).isTrue();

        lottoResult = new LottoResult(6, false);
        assertThat(lottoResult.equals(Rank.FIRST)).isTrue();
    }
}
