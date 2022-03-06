package lotto.domain.lottonumber.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("1보다 작거나 45보다 큰 값으로 객체를 생성하려고 하면 예외를 발생시킨다.")
    @ValueSource(ints = {0, 46})
    void create_exceptionByInvalidValue_Test(final int invalidValue) {
        // given
        final String expectedExceptionMessage = "로또 번호는 1 ~ 45 사이의 자연수여야합니다.";
        // when then
        assertThatThrownBy(() -> LottoNumber.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @ParameterizedTest
    @DisplayName("로또 숫자들을 받아 같은 값이 있는지 확인한다.")
    @MethodSource("provideOtherNumbersAndExpected")
    void hasSameNumberWith_Test(final List<Integer> otherNumbers, final boolean expected) {
        //when
        final LottoNumber one = LottoNumber.from(1);
        final List<LottoNumber> others = otherNumbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toUnmodifiableList());
        final boolean actual = one.hasSameNumberWith(others);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideOtherNumbersAndExpected() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), true),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6, 7), false)
        );
    }
}
