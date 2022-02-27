package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StubRandomLottoNumberGeneratorTest {
    @Test
    @DisplayName("생성자로 전달한 List<Integer> 를 generateLottoNumber 호출 시 그대로 반환")
    void generateLottoNumberTest() {
        // given
        LottoNumberGenerateStrategy fixedLottoNumberGenerator = new StubRandomLottoNumberGenerator(
                List.of(1, 2, 3, 4, 5, 6)
        );

        // when
        List<Integer> actual = fixedLottoNumberGenerator.generateLottoNumbers();

        // then
        assertThat(actual).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
