package lotto.domain;

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
        assertDoesNotThrow(() -> new Lotto(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("로또번호를 정렬해서 저장한다.")
    @Test
    void 로또번호를_정렬해서_저장한다() {
        Lotto lotto = new Lotto(6, 5, 4, 3, 2, 1);
        List<Integer> expectedElements = List.of(1, 2, 3, 4, 5, 6);

        assertThat(lotto.getNumbers()).containsExactlyElementsOf(expectedElements);
    }

    @DisplayName("중복되는 번호가 있는지 검증한다.")
    @Test
    void 중복되는_번호가_있는지_검증한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(1, 1, 2, 3, 4, 5));
    }

    @DisplayName("입력된 번호들이 1부터 45 사이가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("rangeTestArgs")
    void 입력된_번호들이_1부터_45_사이가_아닐_경우_예외가_발생한다(int... numbers) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(numbers));
    }

    @DisplayName("입력된 번호가 6개가 아닌 경우 예외가 발생한다.")
    @Test
    void 입력된_번호가_6개가_아닌_경우_예외가_발생한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(1, 2, 3, 4, 5, 6, 7));
    }

    @DisplayName("특정 숫자가 로또 번호 리스트에 존재하면 true를 반환한다.")
    @Test
    void 특정_숫자가_로또_번호_리스트에_존재하면_true를_반환한다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);

        assertThat(lotto.hasNumber(4)).isEqualTo(true);
    }

    @DisplayName("특정 숫자가 로또 번호 리스트에 없으면 false를 반환한다.")
    @Test
    void 특정_숫자가_로또_번호_리스트에_없으면_false를_반환한다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);

        assertThat(lotto.hasNumber(7)).isEqualTo(false);
    }

    @DisplayName("로또 번호와 일치하는 숫자의 개수를 구한다.")
    @ParameterizedTest
    @MethodSource("matchTestArgs")
    void 로또_번호와_일치하는_숫자의_개수를_구한다(Lotto lotto, int expectedValue) {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);

        assertThat(winningLotto.findMatches(lotto)).isEqualTo(expectedValue);
    }

    static Stream<Arguments> rangeTestArgs() {
        return Stream.of(
                Arguments.of(new int[]{0, 2, 3, 4, 5, 6}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 46})
        );
    }

    static Stream<Arguments> matchTestArgs() {
        return Stream.of(
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(new Lotto(7, 8, 9, 10, 11, 12), 0),
                Arguments.of(new Lotto(6, 8, 9, 10, 11, 12), 1)
        );
    }
}
