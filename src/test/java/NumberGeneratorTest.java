import static org.assertj.core.api.Assertions.assertThat;

import common.NumberGenerator;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

class NumberGeneratorTest {

    private final int size = 3;
    private final int minNumber = 1;
    private final int maxNumber = 10;
    private final List<Integer> numbers = NumberGenerator.generate(size, minNumber, maxNumber);

    @Test
    void 중복되지_않는_숫자들이_뽑힌다() {
        assertThat(new HashSet<>(numbers).size()).isEqualTo(numbers.size());
    }

    @Test
    void 설정한_개수만큼의_숫자들이_뽑힌다() {
        assertThat(numbers.size()).isEqualTo(size);
    }

    @Test
    void 설정한_범위_내의_숫자들이_뽑힌다() {
        assertThat(numbers).allMatch(num -> num >= minNumber && num <= maxNumber);
    }
}
