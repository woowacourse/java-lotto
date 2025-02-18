package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.utils.RandomNumberStrategy;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {
    public class FixedRandomNumberStrategy implements RandomNumberStrategy {
        private final List<Integer> numbers;
        private int index = 0;

        public FixedRandomNumberStrategy(List<Integer> numbers) {
            this.numbers = List.copyOf(numbers);
        }

        @Override
        public int run(final int startNumber, final int endNumber) {
            return numbers.get(index++ % numbers.size());
        }
    }

    @Test
    void 로또번호가_1_45_사이의_숫자이며_6개가_출력되면_통과() {
        // given
        List<Integer> fixedNumbers = List.of(1, 2, 3, 4, 5, 6);
        LottoGenerator lottoGenerator = new LottoGenerator(new FixedRandomNumberStrategy(fixedNumbers));

        // when
        List<Integer> lottoNumbers = lottoGenerator.generateRandomNumbers();

        // then
        assertThat(lottoNumbers).size().isEqualTo(LOTTO_NUM_SIZE);
        assertThat(lottoNumbers).isEqualTo(fixedNumbers);
    }
}
