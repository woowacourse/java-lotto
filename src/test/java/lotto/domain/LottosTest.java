package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.util.LottoGenerator;
import lotto.domain.util.impl.RandomLottoGenerator;
import org.junit.jupiter.api.Test;

public class LottosTest {
    LottoGenerator generator = new TestLottoGenerator();

    @Test
    void 당첨_결과를_구한다() {
        Lottos lottos = new Lottos(generator, 2000);
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1,2,3,4,5,6)), 7);
        assertThat(lottos.calculateWinnings(winningLotto).get(Rank.FIRST))
            .isEqualTo(1);
    }

    @Test
    void 주어진_가격으로_정확한_매수를_구한다() {
        Lottos lottos = new Lottos(new RandomLottoGenerator(), 3000);
        assertThat(lottos.size()).isEqualTo(3);
    }

    @Test
    void 구입_금액이_1000원으로_나누어떨어지지_않을_경우_예외를_반환한다() {
        assertThatThrownBy(() -> new Lottos(generator, 3))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lottos(generator, 3003))
            .isInstanceOf(IllegalArgumentException.class);
    }

    static class TestLottoGenerator implements LottoGenerator {
        private final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        private int index = 0;

        @Override
        public List<LottoNumber> generate(int lottoNumberCount) {
            List<LottoNumber> lottoNumbers = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                lottoNumbers.add(LottoNumber.of(numbers.get(index++)));
            }
            return lottoNumbers;
        }
    }
}
