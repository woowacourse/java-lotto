package lotto.domain;

import static lotto.common.Constants.MIN_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {
    RandomNumberStrategy randomNumberGenerator = new RandomNumberStrategy() {
        private int current = MIN_LOTTO_NUMBER;

        @Override
        public int run(final int startNumber, final int endNumber) {
            return current++;
        }
    };

    @Test
    void 로또번호가_1_45_사이의_숫자이며_6개가_출력되면_통과() {
        // given
        LottoGenerator lottoGenerator = new LottoGenerator(randomNumberGenerator);

        // when
        List<Integer> lottoNumbers = lottoGenerator.generateRandomNumbers();

        // then
        assertThat(lottoNumbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

}
