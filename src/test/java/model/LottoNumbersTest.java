package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
import model.numbers.LottoNumber;
import model.numbers.LottoNumbers;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {

    private static final int VALID_SIZE_OF_NUMBERS = 6;
    private static final int LOWER_BOUND = 1;

    @Test
    void 로또_번호의_개수가_유효하지_않은_경우_예외를_던진다() {
        // given
        List<Integer> numbers = IntStream.rangeClosed(LOWER_BOUND, VALID_SIZE_OF_NUMBERS + 1)
                .boxed()
                .toList();

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(fromIntegerListToLottoNumberList(numbers)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 6개여야 합니다.");
    }

    @Test
    void 로또_번호의_조건이_알맞은_경우_예외를_던지지_않는다() {
        // given
        List<LottoNumber> lottoNumbers = IntStream.rangeClosed(LOWER_BOUND, VALID_SIZE_OF_NUMBERS)
                .boxed()
                .map(LottoNumber::new)
                .toList();

        // when * then
        assertThatCode(() -> new LottoNumbers(lottoNumbers))
                .doesNotThrowAnyException();
    }

    private List<LottoNumber> fromIntegerListToLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
