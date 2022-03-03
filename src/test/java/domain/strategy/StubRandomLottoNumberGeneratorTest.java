package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StubRandomLottoNumberGenerator 테스트")
public class StubRandomLottoNumberGeneratorTest {
    @Test
    @DisplayName("생성자로 전달한 순서를 generateLottoNumber 호출 시 그대로 순환하며 반환한다.")
    void generateLottoNumberTest() {
        // given
        List<Set<Integer>> numbersSequence = List.of(
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(7, 8, 9, 10, 11, 12),
                Set.of(13, 14, 15, 16, 17, 18)
        );
        LottoNumberGenerateStrategy fixedLottoNumberGenerator = new StubRandomLottoNumberGenerator(numbersSequence);

        // when & then
        for (int i = 0; i < 6; i++) {
            int cursor = i % numbersSequence.size();
            Set<Integer> actual = fixedLottoNumberGenerator.generateNumbers();
            assertThat(actual).containsAll(numbersSequence.get(cursor));
        }
    }
}
