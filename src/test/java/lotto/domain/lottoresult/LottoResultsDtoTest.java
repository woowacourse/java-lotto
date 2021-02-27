package lotto.domain.lottoresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.LottoRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultsDtoTest {

    private LottoResults lottoResults;

    @BeforeEach
    void setUp() {
        Lotto userLotto1 = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto userLotto2 = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto userLotto3 = Lotto.of(Arrays.asList(1, 2, 10, 33, 35, 38));
        WinningLotto winningLotto = new WinningLotto(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);

        Lottos lottos1 = Lottos.from(Arrays.asList(userLotto1, userLotto2));
        Lottos lottos2 = Lottos.from(Arrays.asList(userLotto3));
        lottoResults = LottoResults.of(lottos1.match(winningLotto), lottos2.match(winningLotto));
    }

    @DisplayName("DTO 객체의 상태값은 모두 방어적 복사가 되야한다.")
    @Test
    void 방어적_복사_테스트() {
        // given
        LottoResultsDto lottoResultsDto = LottoResultsDto.from(lottoResults);

        // when
        lottoResultsDto.getResult().put(LottoRank.FIRST, 0);

        // then
        assertThat(lottoResults.findNumOfMatchByKey(LottoRank.FIRST)).isEqualTo(1);
    }

}