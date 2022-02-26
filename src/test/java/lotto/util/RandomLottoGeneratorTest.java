package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.LottoNumber;
import lotto.model.LottoNumbers;
import org.junit.jupiter.api.Test;

public class RandomLottoGeneratorTest {

    RandomUtil randomLottoGenerator = new RandomNumbersGenerator();

    @Test
    void 중복_테스트() {
        LottoNumbers numbers = randomLottoGenerator.generate();
        List<Integer> actual = numbers.getLottoNumbers().stream()
                .map(LottoNumber::getLottoNumber)
                .distinct()
                .collect(Collectors.toList());
        assertThat(actual.size()).isEqualTo(numbers.getLottoNumbers().size());
    }

    @Test
    void 범위_테스트() {
        LottoNumbers numbers = randomLottoGenerator.generate();
        numbers.getLottoNumbers().forEach(number -> {
            assertThat(number.getLottoNumber()).isGreaterThan(0).isLessThan(46);
        });
    }
}
