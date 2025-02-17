package domain;

import static error.ErrorMessage.INVALID_DUPLICATE_NUMBER;
import static error.ErrorMessage.INVALID_NUMBERS_SIZE;
import static error.ErrorMessage.INVALID_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Nested
    @DisplayName("숫자가 6개가 아닐 경우 예외를 발생시킨다.")
    class validateLength {

        @DisplayName("숫자가_6개를_초과한_경우_예외가_발생해야_한다")
        @Test
        void over_6_length() {
            assertThrowValidateNumber(List.of(1, 2, 3, 4, 5, 6, 7),
                INVALID_NUMBERS_SIZE.getMessage());
        }

        @DisplayName("숫자가_6개_미만인_경우_예외가_발생해야_한다")
        @Test
        void less_6_length() {
            assertThrowValidateNumber(List.of(1, 2, 3, 4, 5),
                INVALID_NUMBERS_SIZE.getMessage());
        }

        @DisplayName("숫자가_6개인_경우_로또객체가_성공적으로_생성되어야_한다")
        @Test
        void is_6_length() {
            List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);
            Lotto lotto = Lotto.from(expectedNumbers);
            List<Integer> lottoNumbers = lotto.getNumbers();
            assertEquals(expectedNumbers, lottoNumbers);
        }
    }

    @DisplayName("중복되는 숫자를 입력할 경우 예외를 발생시킨다. ")
    @Test
    void validate_duplicate_number() {
        assertThrowValidateNumber(List.of(1, 1, 2, 3, 4, 5),
            INVALID_DUPLICATE_NUMBER.getMessage());
    }

    @Nested
    @DisplayName("숫자 생성 테스트")
    class validateGenerateNumber {

        @DisplayName("1~45 사이의 숫자일 경우, 로또 객체는 성공적으로 생성되어야 한다")
        @Test
        void valid_number_range() {
            List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 45);
            Lotto lotto = Lotto.from(expectedNumbers);
            List<Integer> numbers = lotto.getNumbers();
            assertEquals(expectedNumbers, numbers);
        }

        @DisplayName("숫자가 45보다 크다면, 예외를 발생시켜야 한다")
        @Test
        void number_over_45() {
            assertThrowValidateNumber(List.of(1, 2, 3, 4, 5, 46),
                INVALID_NUMBER_RANGE.getMessage());
        }

        @DisplayName("숫자가 1보다 작다면, 예외를 발생시켜야 한다")
        @Test
        void number_under_1() {
            assertThrowValidateNumber(List.of(0, 2, 3, 4, 5, 45),
                INVALID_NUMBER_RANGE.getMessage());
        }

        @DisplayName("숫자가 음수라면, 예외를 발생시켜야 한다.")
        @Test
        void validate_negative_number() {
            assertThrowValidateNumber(List.of(-1, 2, 3, 4, 5, 45),
                INVALID_NUMBER_RANGE.getMessage());
        }
    }

    private void assertThrowValidateNumber(List<Integer> expectedNumbers, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto.from(expectedNumbers);
        });
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

}
