package domain.strategy;

import domain.LottoNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoNumbersGeneratorTest {

    private LottoNumberStrategy strategy;

    @Test
    @DisplayName("자동 생성된 로또 번호는 6자리")
    void generatedLottoNumbersSize() {
        strategy = new RandomLottoNumberStrategy();
        assertThat(strategy.generate().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("자동 생성된 로또 번호 미중복")
    void generatedLottoNumbersDistinct() {
        strategy = new RandomLottoNumberStrategy();
        long size = strategy.generate().stream()
                .distinct()
                .count();

        assertThat(size).isEqualTo(6);
    }

    @Test
    @DisplayName("자동 생성된 로또 번호 정렬")
    void sortLottoNumbers() {
        strategy = new RandomLottoNumberStrategy();
        LottoNumbers lottoNumbers = new LottoNumbers(strategy.generate());
        List<Integer> numbers = lottoNumbers.getLottoNumbers();

        Assertions.assertThat(numbers).isSorted();
    }
}
