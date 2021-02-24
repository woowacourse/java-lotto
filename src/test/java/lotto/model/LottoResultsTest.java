package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultsTest {

    @DisplayName("두 LottoResult를 합쳐서 하나의 결과가 나와야한다")
    @Test
    void 로또_결과_머지_테스트() {
        // given
        Lotto userLotto1 = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto userLotto2 = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto userLotto3 = Lotto.of(Arrays.asList(1, 2, 10, 33, 35, 38));
        WinningLotto winningLotto = new WinningLotto(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);

        Lottos lottos1 = Lottos.from(Arrays.asList(userLotto1, userLotto2));
        Lottos lottos2 = Lottos.from(Arrays.asList(userLotto3));

        // when
        LottoResult lottoResult1 = lottos1.match(winningLotto);
        LottoResult lottoResult2 = lottos2.match(winningLotto);
        LottoResults lottoResults = LottoResults.of(lottoResult1, lottoResult2);

        // then
        assertThat(lottoResults.findNumOfMatchByKey(LottoRank.FIRST)).isEqualTo(1);
        assertThat(lottoResults.findNumOfMatchByKey(LottoRank.NO_MATCH)).isEqualTo(1);
        assertThat(lottoResults.findNumOfMatchByKey(LottoRank.SECOND)).isEqualTo(1);
    }
}