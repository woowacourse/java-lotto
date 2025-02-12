package domain.numbergenerator;


import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {
    @DisplayName("중복되지 않는 6개의 숫자가 나온다")
    @Test
    void 중복되지_않는_6개의_숫자가_나온다() {
        // given
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        // when
        List<Integer> selectedNumber = randomNumberGenerator.generateNumber();

        // then
        Assertions.assertThat(selectedNumber.stream().distinct()).hasSize(6);
    }
}