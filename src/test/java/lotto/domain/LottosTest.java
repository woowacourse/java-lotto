package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lotto.domain.util.impl.RandomLottoGenerator;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottosTest {

    @Test
    void 당첨_결과를_구한다() {
        Iterator<List<Integer>> numsIterator = getNumsIterator();
        Lottos lottos = new Lottos(i -> numsIterator.next().stream()
                .map(LottoNumber::of)
                .toList(), 6000);
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Map<Rank, Long> rankLongMap = lottos.calculateWinnings(winningLotto);

        SoftAssertions.assertSoftly(softly -> {
            assertThat(rankLongMap.get(Rank.FIFTH)).isEqualTo(1);
            assertThat(rankLongMap.get(Rank.SECOND)).isEqualTo(1);
            assertThat(rankLongMap.get(Rank.THIRD)).isEqualTo(1);
            assertThat(rankLongMap.get(Rank.FOURTH)).isEqualTo(1);
            assertThat(rankLongMap.get(Rank.FIFTH)).isEqualTo(1);
        });
    }

    @Test
    void 주어진_가격으로_정확한_매수를_구한다() {
        Lottos lottos = new Lottos(new RandomLottoGenerator(), 3000);
        assertThat(lottos.size()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 3003})
    void 구입_금액이_1000원으로_나누어떨어지지_않을_경우_예외를_반환한다(int payment) {
        assertThatThrownBy(() -> new Lottos(new RandomLottoGenerator(), payment))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Iterator<List<Integer>> getNumsIterator() {
        return List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 8),
                List.of(1, 2, 3, 4, 8, 9),
                List.of(1, 2, 3, 8, 9, 10),
                List.of(1, 2, 8, 9, 10, 11)
        ).iterator();
    }
}
