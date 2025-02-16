package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
import model.numbers.LottoNumbers;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {

    private static final int VALID_SIZE_OF_NUMBERS = 6;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    @Test
    void 로또_번호가_하한보다_작은_숫자를_포함하는_경우_예외를_던진다() {
        // given
        List<Integer> invalidNumbers = List.of(LOWER_BOUND - 1, 1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    void 로또_번호가_상한보다_큰_숫자를_포함하는_경우_예외를_던진다() {
        // given
        List<Integer> invalidNumbers = List.of(UPPER_BOUND + 1, 1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    void 로또_번호의_개수가_유효하지_않은_경우_예외를_던진다() {
        // given
        List<Integer> numbers = IntStream.rangeClosed(LOWER_BOUND, VALID_SIZE_OF_NUMBERS + 1)
                .boxed()
                .toList();

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    void 로또_번호의_조건이_알맞은_경우_예외를_던지지_않는다() {
        // given
        List<Integer> numbers = IntStream.rangeClosed(LOWER_BOUND, VALID_SIZE_OF_NUMBERS)
                .boxed()
                .toList();

        // when * then
        assertThatCode(() -> new LottoNumbers(numbers))
                .doesNotThrowAnyException();
    }
}
