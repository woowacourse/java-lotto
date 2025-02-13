package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @ParameterizedTest
    @DisplayName("숫자가 6개가 아니면 예외가 발생한다.")
    @MethodSource("makeNotSixNumbers")
    void createLottoWithWrongSizeNumbers(List<Integer> numbers) {
        assertThatThrownBy(() ->
                new Lotto(numbers)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자가 6개가 아닙니다.");
    }

    private static Stream<Arguments> makeNotSixNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @ParameterizedTest
    @DisplayName("숫자가 범위를 벗어나면 예외가 발생한다.")
    @MethodSource("makeWrongRangeNumbers")
    void createLottoWithOutOfRangeNumber(List<Integer> numbers) {
        assertThatThrownBy(() ->
                new Lotto(numbers)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1 이상 45 이하만 가능합니다.");
    }

    private static Stream<Arguments> makeWrongRangeNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(-1, 1, 2, 3, 4, 5))
        );
    }

    @ParameterizedTest
    @DisplayName("중복된 숫자가 있으면 예외가 발생한다.")
    @MethodSource("makeDuplicateNumbers")
    void createLottoWithDuplication(List<Integer> numbers) {
        assertThatThrownBy(() ->
                new Lotto(numbers)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 숫자가 존재합니다.");
    }

    private static Stream<Arguments> makeDuplicateNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 5)),
                Arguments.of(List.of(1, 2, 3, 5, 5, 5))
        );
    }

    @DisplayName("다른 로또와 매칭되는 번호의 개수를 계산해 반환한다.")
    @Test
    void 다른_로또와_매칭되는_번호의_개수를_계산해_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto otherLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.calculateMatchingCount(otherLotto)).isEqualTo(6);
    }

    @DisplayName("번호를 알려주면 존재 여부를 알려준다.")
    @CsvSource(value = {"1,true", "7,false"})
    @ParameterizedTest
    void 번호를_알려주면_존재_여부를_알려준다(int number, boolean expected) {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.has(number)).isEqualTo(expected);
    }

}
