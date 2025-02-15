package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import lotto.util.NumberGenerator;
import lotto.util.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

public class LottosTest {
    @Test
    void 당첨_결과를_구한다() {
        NumberGenerator generator = new TestLottoNumberGenerator();
        Lottos lottos = new Lottos(generator, 2000);
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1,2,3,4,5,6)), 7);
        assertThat(lottos.getRankCount(winningNumbers).get(Rank.FIRST))
            .isEqualTo(1);
    }

    @Test
    void 주어진_가격으로_정확한_매수를_구한다() {
        Lottos lottos = new Lottos(new RandomNumberGenerator(), 3000);
        assertThat(lottos.getTicketCount()).isEqualTo(3);
    }

    @Test
    void 구입_금액이_1000원으로_나누어떨어지지_않을_경우_예외를_반환한다() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        assertThatThrownBy(() -> new Lottos(generator, 3))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lottos(generator, 3003))
            .isInstanceOf(IllegalArgumentException.class);
    }

    static class TestLottoNumberGenerator implements NumberGenerator {
        private final List<Integer>numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        private int index = 0;

        @Override
        public int generate(int minimum, int maximum) {
            return numbers.get(index++);
        }
    }
}
