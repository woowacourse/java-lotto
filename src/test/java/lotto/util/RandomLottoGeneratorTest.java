package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.LottoNumber;
import lotto.model.LottoNumbers;
import org.junit.jupiter.api.Test;

public class RandomLottoGeneratorTest {

    LottoUtil randomLottoGenerator = new LottoNumbersGenerator();

    @Test
    void 중복_테스트() {
        LottoNumbers numbers = randomLottoGenerator.generateLottoNumbers(1,45);
        List<Integer> actual = numbers.getLottoNumbers().stream()
                .map(LottoNumber::getLottoNumber)
                .distinct()
                .collect(Collectors.toList());
        assertThat(actual.size()).isEqualTo(numbers.getLottoNumbers().size());
    }

    @Test
    void 범위_테스트() {
        LottoNumbers numbers = randomLottoGenerator.generateLottoNumbers(1,45);
        numbers.getLottoNumbers().forEach(number -> {
            assertThat(number.getLottoNumber()).isGreaterThan(0).isLessThan(46);
        });
    }
}
