package lotto.domain;

import lotto.constant.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoTest {

    @DisplayName("로또 객체를 생성할 수 있다.")
    @Test
    void 로또_객체를_생성할_수_있다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        assertDoesNotThrow(() -> new Lotto(numbers));
    }

    @DisplayName("로또번호를 정렬해서 저장한다.")
    @Test
    void 로또번호를_정렬해서_저장한다() {
        List<Integer> numbers = List.of(6, 5, 4, 3, 2, 1);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers()).containsExactlyElementsOf(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("중복되는 번호가 있는지 검증한다.")
    @Test
    void 중복되는_번호가_있는지_검증한다() {
        List<Integer> numbers = List.of(1, 1, 2, 3, 4, 5);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
    }

    @DisplayName("입력된 번호들이 1부터 45사이인지 검증한다.")
    @ParameterizedTest
    @MethodSource("rangeTestArgs")
    void 입력된_번호들이_1부터_45사이인지_검증한다(List<Integer> numbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(ExceptionMessage.OUT_OF_RANGE.getContent());
    }

    @DisplayName("입력된 번호가 6개인지 검증한다.")
    @Test
    void 입력된_번호가_6개인지_검증한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(ExceptionMessage.INVALID_NUMBER_COUNT.getContent());
    }

    @DisplayName("특정 숫자가 로또 번호 리스트에 존재하는지 확인한다.")
    @Test
    void 특정_숫자가_로또_번호_리스트에_존재하는지_확인한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.hasNumber(4)).isEqualTo(true);
        assertThat(lotto.hasNumber(7)).isEqualTo(false);
    }

    @DisplayName("로또 번호와 일치하는 숫자의 개수를 구한다.")
    @ParameterizedTest
    @MethodSource("matchTestArgs")
    void 로또_번호와_일치하는_숫자의_개수를_구한다(Lotto lotto, int expectedValue) {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);

        assertThat(winningLotto.findMatches(lotto)).isEqualTo(expectedValue);
    }

    static Stream<Arguments> rangeTestArgs() {
        return Stream.of(
                Arguments.of(List.of(0, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46))
        );
    }

    static Stream<Arguments> matchTestArgs() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), 5),
                Arguments.of(new Lotto(List.of(7, 8, 9, 10, 11, 12)), 0),
                Arguments.of(new Lotto(List.of(6, 8, 9, 10, 11, 12)), 1)
        );
    }
}