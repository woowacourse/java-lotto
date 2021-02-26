package lotto.domain;

import lotto.exception.NumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberTest {
    @Test
    @DisplayName("로또 번호 유효성 통과 테스트")
    void successLottoNumber() {
        int successIntegerNumber = 3;
        Number successNumber = Number.from(successIntegerNumber);
        assertThat(successNumber).isEqualTo(Number.from(successIntegerNumber));
    }

    @Test
    @DisplayName("로또 번호 초과 테스트")
    void excessLottoNumber() {
        int excessIntegerNumber = 46;
        assertThatThrownBy(() -> Number.from(excessIntegerNumber))
            .isInstanceOf(NumberException.class)
            .hasMessageContaining(Number.INVALID_NUMBER_MESSAGE);
    }

    @Test
    @DisplayName("로또 번호 미 테스트")
    void lackLottoNumber() {
        int lackIntegerNumber = 0;
        assertThatThrownBy(() -> Number.from(lackIntegerNumber))
            .isInstanceOf(NumberException.class)
            .hasMessageContaining(Number.INVALID_NUMBER_MESSAGE);
    }
}
