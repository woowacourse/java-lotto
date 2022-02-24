package lotto.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static lotto.domain.vo.LottoNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("1부터 45까지의 자연수가 아닌 값으로 객체를 생성할 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"0", "-1", "1.2", "a"})
    void create_exceptionByInvalidValue(final String value) {
        // given
        final String expectedExceptionMessage = "로또 번호는 1 ~ 45 사이의 자연수여야합니다.";
        // when then
        assertThatThrownBy(() -> LottoNumber.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @ParameterizedTest
    @DisplayName("로또 숫자들을 받아 같은 값이 있는지 확인한다.")
    @MethodSource("provideOtherNumbersAndExpected")
    void hasSameNumberWith(final List<LottoNumber> otherNumbers, final boolean expected) {
        //when
        final boolean actual = ONE.hasSameNumberWith(otherNumbers);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideOtherNumbersAndExpected() {
        return Stream.of(
                Arguments.of(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX), true),
                Arguments.of(Arrays.asList(TWO, THREE, FOUR, FIVE, SIX, SEVEN), false)
        );
    }
}
