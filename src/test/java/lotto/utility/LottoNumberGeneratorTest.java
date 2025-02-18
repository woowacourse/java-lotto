package lotto.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGeneratorTest {

    static class TestRandomNumberGenerator implements RandomNumberGenerator {
        private final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int index = 0;

        @Override
        public int generate() {
            return numbers.get(index++);
        }
    }

    @DisplayName("입력한 최대 범위까지의 난수 리스트를 생성한다.")
    @Test
    void 입력한_최대_범위까지의_난수_리스트를_생성한다() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        final int MAX_NUMBER = 45;
        final int NUMBER_COUNT = 6;
        List<Integer> randomNumbers = lottoNumberGenerator.generateNumbers(MAX_NUMBER, NUMBER_COUNT);

        assertThat(randomNumbers).allSatisfy(number -> assertThat(number).isStrictlyBetween(0, 46));
    }

    @DisplayName("Random API를 사용해 무작위로 번호를 생성한다.")
    @Test
    void Random_API를_사용해_무작위로_번호를_생성한다() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        List<Integer> generatedNumbers = lottoNumberGenerator.generateNumbers(new TestRandomNumberGenerator(), 6);
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThat(generatedNumbers).isEqualTo(expectedNumbers);
    }
}
