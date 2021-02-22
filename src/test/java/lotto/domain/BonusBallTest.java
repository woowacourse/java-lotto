package lotto.domain;

import lotto.exception.BonusBall.BonusBallDuplicatedException;
import lotto.exception.BonusBall.BonusBallException;
import lotto.exception.BonusBall.BonusBallScopeException;
import lotto.exception.Lotto.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BonusBallTest {
    private static final Lotto lotto = Lotto.from("1,2,3,4,5,6");

    private static Stream<Arguments> provideAllExceptionCase() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(0),
                Arguments.of(46)
        );
    }

    @Test
    @DisplayName("보너스 볼 중복될 경우")
    void bonusBallDuplicated() {
        assertThatThrownBy(() -> {
            new BonusBall(1, lotto);
        }).isInstanceOf(BonusBallDuplicatedException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1 ~ 45 이외의 수일 경우")
    void bonusBallOutOfScope(int input) {
        assertThatThrownBy(() -> {
            new BonusBall(input, lotto);
        }).isInstanceOf(BonusBallScopeException.class);
    }

    @ParameterizedTest
    @DisplayName("모든 예외 상황 한 번에 테스트")
    @MethodSource("provideAllExceptionCase")
    void numbersExceptionValid(int input) {
        assertThatThrownBy(() -> {
            new BonusBall(input, lotto);
        }).isInstanceOf(BonusBallException.class);
    }
}