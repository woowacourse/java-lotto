package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoTicketCountTest {
    @Test
    @DisplayName("객체를 잘 생성하는지 테스트")
    void generate_Count() {
        String input = "1";

        assertThatCode(()->new LottoTicketCount(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음수를 받을때 오류테스트")
    void throw_validatePositiveNumber() {
        assertThatThrownBy(()->new LottoTicketCount("-1")).isInstanceOf(IllegalArgumentException.class);
    }
    @ParameterizedTest
    @ValueSource(strings = {"1.1", "가", "", " "})
    @DisplayName("정수 이외의 값 받을때 오류테스트")
    void throw_validateIntegerNumberFormat(String input) {
        assertThatThrownBy(()->new LottoTicketCount(input)).isInstanceOf(NumberFormatException.class);
    }
}