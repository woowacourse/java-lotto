import domain.RandomLottoNumberStrategy;
import domain.LottoNumberStrategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoNumberStrategyTest {

    private final LottoNumberStrategy numberGenerator = new RandomLottoNumberStrategy();

    @Test
    @DisplayName("LottoNumbers 로또 숫자 개수 6개인지 검증")
    void generatedLottoNumbersSize() {
        assertThat(numberGenerator.generate().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 숫자 중복이 없는지 검증")
    void generatedLottoNumbersDistinct() {
        long size = numberGenerator.generate().stream()
                .distinct()
                .count();

        assertThat(size).isEqualTo(6);
    }
}
