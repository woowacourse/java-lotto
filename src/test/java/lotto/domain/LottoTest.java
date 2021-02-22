package lotto.domain;

import lotto.exception.Lotto.LottoException;
import lotto.exception.Lotto.LottoNumberCountException;
import lotto.exception.Lotto.LottoNumberNullException;
import lotto.exception.Lotto.LottoNumberScopeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTest {

    private static final Lotto winningLotto = Lotto.from("1,2,3,4,5,6");

    private static Stream<Arguments> provideAllExceptionCase() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6,7"),
                Arguments.of("1,2,3,4,5,46"),
                Arguments.of((String) null)
        );
    }

    @Test
    @DisplayName("보너스 볼 포함하는 경우")
    void bonusBallDuplicated() {
        Lotto lotto = Lotto.from("11,12,13,14,15,16");
        assertEquals(lotto.isContainNumber(11), true);
    }

    @ParameterizedTest
    @DisplayName("입력된 번호의 수가 6개가 아닐 경우")
    @ValueSource(strings = {"1,2,3,4,5,6,7", "1,2,3"})
    void numbersCountValid(String numbers) {
        assertThatThrownBy(() -> {
            Lotto.from(numbers);
        }).isInstanceOf(LottoNumberCountException.class);
    }

    @ParameterizedTest
    @DisplayName("입력된 번호의 수가 1 ~ 45의 수가 아닐 경우")
    @ValueSource(strings = {"1,2,3,4,5,46", "0,1,2,3,4,5"})
    void numbersScopeValid(String numbers) {
        assertThatThrownBy(() -> {
            Lotto.from(numbers);
        }).isInstanceOf(LottoNumberScopeException.class);
    }

    @Test
    @DisplayName("입력된 번호가 null일 경우")
    void numbersNullValid() {
        assertThatThrownBy(() -> {
            Lotto.from((String) null);
        }).isInstanceOf(LottoNumberNullException.class);
    }

    @ParameterizedTest
    @DisplayName("모든 예외 상황 한 번에 테스트")
    @MethodSource("provideAllExceptionCase")
    void numbersExceptionValid(String input) {
        assertThatThrownBy(() -> {
            Lotto.from(input);
        }).isInstanceOf(LottoException.class);
    }
}