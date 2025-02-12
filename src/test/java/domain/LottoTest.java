package domain;

import static error.ErrorMessage.INVALID_DUPLICATE_NUMBER;
import static error.ErrorMessage.INVALID_NUMBERS_SIZE;
import static error.ErrorMessage.INVALID_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("숫자가 6개가 아닐 경우 예외를 발생시킨다.")
    @Test
    void 갯수가_6이_아닌_경우() {

        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto lotto = Lotto.create(expectedNumbers);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_NUMBERS_SIZE.getMessage());
    }

    @DisplayName("숫자가 6개인 경우 성공")
    @Test
    void 성공적으로_생성_되었을_경우() {
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.create(expectedNumbers);
        List<Integer> lottoNumbers = lotto.getNumbers();
        assertEquals(expectedNumbers, lottoNumbers);
    }

    @DisplayName("중복되는 숫자를 입력할 경우 예외를 발생시킨다. ")
    @Test
    void 숫자가_중복되는_경우() {
        List<Integer> expectedNumbers = List.of(1, 1, 2, 3, 4, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto lotto = Lotto.create(expectedNumbers);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_DUPLICATE_NUMBER.getMessage());
    }

    @DisplayName("1~45 사이의 숫자일 경우, 테스트를 통과한다.")
    @Test
    void 숫자_범위가_유효한_경우() {
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 45);
        Lotto lotto = Lotto.create(expectedNumbers);
        List<Integer> numbers = lotto.getNumbers();
        assertEquals(expectedNumbers, numbers);
    }

    @DisplayName("1~45 사이 숫자가 아닐 경우 예외를 발생시킨다.")
    @Test
    void 숫자_범위가_유효하지_않은_경우() {
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 46);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto lotto = Lotto.create(expectedNumbers);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_NUMBER_RANGE.getMessage());
    }

}
