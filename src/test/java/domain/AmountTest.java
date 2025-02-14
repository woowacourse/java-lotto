package domain;

import static global.exception.ExceptionMessage.INVALID_INTEGER;
import static global.exception.ExceptionMessage.INVALID_POSITIVE;
import static global.exception.ExceptionMessage.INVALID_UNIT_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AmountTest {

    @Nested
    class 금액_검증_테스트 {
        @Test
        void 금액이_정수가_아니면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                new Amount("우택호");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_INTEGER.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"-5", "0"})
        void 금액이_양수가_아니면_예외가_발생한다(String input) {
            assertThatThrownBy(() -> {
                new Amount(input);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_POSITIVE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1500", "1234"})
        void 금액이_1000원_단위가_아니면_예외가_발생한다(String input) {
            assertThatThrownBy(() -> {
                new Amount(input);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_UNIT_PRICE.getMessage());
        }
    }

    @Test
    void 수익률_계산_테스트() {
        //given
        Amount amount = new Amount("14000");

        //when-then
        assertThat(amount.calculateProfit(5000)).isEqualTo(0.35);
    }
}