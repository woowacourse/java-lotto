package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.LottoNumber;
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
        List<Set<LottoNumber>> numbersSequence = List.of(
                Set.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3), LottoNumber.from(4),
                        LottoNumber.from(5), LottoNumber.from(6)),
                Set.of(LottoNumber.from(7), LottoNumber.from(8), LottoNumber.from(9), LottoNumber.from(10),
                        LottoNumber.from(11), LottoNumber.from(12)),
                Set.of(LottoNumber.from(13), LottoNumber.from(14), LottoNumber.from(15), LottoNumber.from(16),
                        LottoNumber.from(17), LottoNumber.from(18))
        );
        LottoNumberGenerateStrategy fixedLottoNumberGenerator = StubRandomLottoNumberGenerator.from(numbersSequence);

        // when & then
        for (int i = 0; i < 6; i++) {
            int cursor = i % numbersSequence.size();
            Set<LottoNumber> actual = fixedLottoNumberGenerator.generateNumbers();
            assertThat(actual).containsAll(numbersSequence.get(cursor));
        }
    }
}
