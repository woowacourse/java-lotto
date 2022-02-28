package lotto.util;

import lotto.model.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomLottoGeneratorTest {

    @Test
    void 중복_테스트() {
        Lotto lotto = RandomLottoGenerator.generate();
        List<Integer> actual = lotto.getNumbers()
                .stream()
                .distinct()
                .collect(Collectors.toList());
        assertThat(actual.size()).isEqualTo(lotto.getNumbers().size());
    }

    @Test
    void 범위_테스트() {
        Lotto lotto = RandomLottoGenerator.generate();
        lotto.getNumbers().forEach(number -> {
            assertThat(number).isGreaterThan(0).isLessThan(46);
        });
    }
}
