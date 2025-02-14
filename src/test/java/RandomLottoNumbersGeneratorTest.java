import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomLottoNumbersGeneratorTest {
    private final RandomLottoNumbersGenerator randomLottoNumbersGenerator = new RandomLottoNumbersGenerator();

    @Test
    @DisplayName("중복되지않는 여섯 개의 숫자 리스트가 생성된다")
    void generateCorrectNumbers() {
        // given

        // when
        List<Integer> numbers = randomLottoNumbersGenerator.generate();

        // then
        assertThat(numbers).doesNotHaveDuplicates();
        assertThat(numbers.size()).isEqualTo(6);
    }
}
