package lottogame.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        List<Integer> values = Arrays.asList(8, 21, 23, 41, 42, 43);
        lotto = new Lotto(new LottoNumber(values));
    }

    @DisplayName("로또가 당첨 개수가 올바른 지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"8, 21, 23, 41, 42, 43:7:6",
            "3, 5, 11, 16, 32, 38:8:0",
            "7, 11, 16, 35, 36, 44:5:0",
            "1, 8, 11, 31, 41, 42:3:3"}, delimiter = ':')
    void 일치하는_번호_갯수(String values, int bonus, int matchCount) {
        List<Integer> numbers = Arrays.stream(values.split(", "))
                .mapToInt(value -> Integer.parseInt(value))
                .boxed()
                .collect(Collectors.toList());
        WinningLotto winningLotto = new WinningLotto(numbers, bonus);
        assertThat(lotto.matchCount(winningLotto)).isEqualTo(matchCount);
    }
}
