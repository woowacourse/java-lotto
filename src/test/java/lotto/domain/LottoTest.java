package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.exception_message.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {

    @DisplayName("로또 객체를 생성할 수 있다.")
    @Test
    void 로또_객체를_생성할_수_있다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers()).containsExactlyElementsOf(numbers);
    }

    @DisplayName("입력된 로또 번호를 정렬해서 저장한다.")
    @Test
    void 입력된_로또_번호를_정렬해서_저장한다() {
        List<Integer> numbers = List.of(6, 5, 4, 3, 2, 1);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers()).containsExactlyElementsOf(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("입력된 로또 번호에서 중복 숫자가 존재하는지 검증한다.")
    @Test
    void 입력된_로또_번호에서_중복_숫자가_존재하는지_검증한다() {
        List<Integer> numbers = List.of(1, 1, 2, 3, 4, 5);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
    }

    @DisplayName("입력된 로또 번호들이 정해진 범위 내에 있는지 검증한다.")
    @Test
    void 입력된_로또_번호들이_정해진_범위_내에_있는지_검증한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(ExceptionMessage.OUT_OF_RANGE.getContent());
    }

    @DisplayName("입력된 로또 번호가 정해진 개수인지 검증한다.")
    @Test
    void 입력된_로또_번호가_정해진_개수인지_검증한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(ExceptionMessage.INVALID_NUMBER_COUNT.getContent());
    }

    @DisplayName("현재 로또에 특정 번호가 포함되어 있는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"1,true", "45,false"})
    void 현재_로또에_특정_번호가_포함되어_있는지_확인한다(int givenNumber, boolean expectedResult) {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.hasNumber(givenNumber)).isEqualTo(expectedResult);
    }

    @DisplayName("다른 로또의 로또 번호와 몇 개의 숫자가 매칭되는지 확인한다.")
    @Test
    void 다른_로또의_로또_번호와_몇_개의_숫자가_매칭되는지_확인한다() {
        Lotto currentLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto otherLotto = new Lotto(List.of(1, 2, 3, 43, 44, 45));

        assertThat(currentLotto.findMatches(otherLotto)).isEqualTo(3);
    }
}