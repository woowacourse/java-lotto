package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoTest {

    private static Stream<Arguments> twoLottosForMatchedEachOther() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(40, 41, 42, 43, 44, 45)), 0),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 43, 44, 45)), 3),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6));
    }

    @DisplayName("두 로또의 일치하는 수 반환")
    @ParameterizedTest(name ="{displayName} expected={2}")
    @MethodSource("twoLottosForMatchedEachOther")
    void matchedEachOther_rightResult(Lotto lotto, Lotto otherLotto, int expected) {
        assertThat(lotto.matchedEachOther(otherLotto)).isEqualTo(expected);
    }

    @DisplayName("해당 숫자 볼의 포함 여부 반환")
    @ParameterizedTest(name = "{displayName} expected={1}")
    @CsvSource(value = {"20,true", "45,false"}, delimiter = ',')
    void lotto_hasBonusBall(int bonusNumber, boolean expected) {
        Lotto lotto = new Lotto(List.of(10, 15, 20, 25, 30, 35));
        LottoBall bonusBall = new LottoBall(bonusNumber);
        assertThat(lotto.hasBall(bonusBall)).isEqualTo(expected);
    }

    @DisplayName("숫자가 6개 미만일 시 예외 발생")
    @Test
    void numbersSizeUnderSix_constructor_throwsException() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자가 6개 초과 시 예외 발생")
    @Test
    void numbersSizeOverSix_constructor_throwsException() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복 숫자가 존재할 시 예외 발생")
    @Test
    void numbersHasDuplicated_constructor_throwsException() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
