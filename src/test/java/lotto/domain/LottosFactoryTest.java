package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottosFactoryTest {

    @DisplayName("수동 로또 생성 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6", "43, 23, 36, 12, 10, 05"})
    void makeLottos(int one, int two, int three, int four, int five, int six) {
        Lotto lotto = LottoFactory
            .createManual(one + ", " + two + ", " + three + ", " + four + ", " + five + ", " + six);
        assertThat(lotto.contains(Ball.of(one))).isTrue();
        assertThat(lotto.contains(Ball.of(two))).isTrue();
        assertThat(lotto.contains(Ball.of(three))).isTrue();
        assertThat(lotto.contains(Ball.of(four))).isTrue();
        assertThat(lotto.contains(Ball.of(five))).isTrue();
        assertThat(lotto.contains(Ball.of(six))).isTrue();
    }

    @DisplayName("수동 로또 생성 실패 - 개수 초과")
    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6, 7", "1, 2, 3, 4, 5"})
    void differentCount(String string) {
        assertThatThrownBy(() -> LottoFactory.createManual(string))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("개수");
    }
}
