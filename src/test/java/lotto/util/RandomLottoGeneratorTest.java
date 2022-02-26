package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.LottoNumber;
import org.junit.jupiter.api.Test;

public class RandomLottoGeneratorTest {

    RandomUtil randomLottoGenerator = new RandomNumbersGenerator();

    @Test
    void 중복_테스트() {
        List<LottoNumber> numbers = randomLottoGenerator.generate();
        List<Integer> actual = numbers.stream()
                .map(LottoNumber::getLottoNumber)
                .distinct()
                .collect(Collectors.toList());
        assertThat(actual.size()).isEqualTo(numbers.size());
    }

    @Test
    void 범위_테스트() {
        List<LottoNumber> numbers = randomLottoGenerator.generate();
        numbers.forEach(number -> {
            assertThat(number.getLottoNumber()).isGreaterThan(0).isLessThan(46);
        });
    }
}
