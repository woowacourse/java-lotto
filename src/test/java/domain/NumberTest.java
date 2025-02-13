package domain;

import static domain.Number.MAX_NUMBER;
import static domain.Number.MIN_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberTest {

    @ParameterizedTest
    @CsvSource({"-1", "0", "46", "47"})
    void Number의_값이_1부터_45_사이의_수가_아니라면_예외를_반환한다(int value) {
        assertThatThrownBy(() -> new Number(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("번호는 " + MIN_NUMBER + " 이상 " + MAX_NUMBER + "이하여야 합니다.");

    }
}