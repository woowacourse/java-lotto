package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.constant.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또 객체를 생성할 수 있다.")
    @Test
    void 로또_객체를_생성할_수_있다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers()).containsExactlyElementsOf(numbers);
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
    @Test
    void 입력된_번호들이_1부터_45사이인지_검증한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);
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
}