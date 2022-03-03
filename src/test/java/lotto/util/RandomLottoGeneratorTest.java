package lotto.util;

import lotto.model.Lotto;
import lotto.model.LottoNumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomLottoGeneratorTest {

    @Test
    @DisplayName("중복 테스트")
    void duplicateTest() {
        Lotto lotto = RandomLottoGenerator.generateAutoLotto();
        List<LottoNumber> actual = lotto.getNumbers()
                .stream()
                .distinct()
                .collect(Collectors.toList());
        assertThat(actual.size()).isEqualTo(lotto.getNumbers().size());
    }
}
