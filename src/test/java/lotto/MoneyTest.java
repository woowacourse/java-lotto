package lotto;

import lotto.exception.InvalidPaymentException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 제대로된_금액을_입력받은_경우() {
        assertDoesNotThrow(() -> new Money("1000"));
    }

    @Test
    void 빈_문자열을_입력받은_경우_예외_발생() {
        assertThrows(InvalidPaymentException.class, () -> new Money(""));
    }

    @Test
    void 공백을_입력받은_경우_예외_발생() {
        assertThrows(InvalidPaymentException.class, () -> new Money(" "));
    }

    @Test
    void 숫자가_아닌_값을_입력받은_경우_예외_발생() {
        assertThrows(InvalidPaymentException.class, () -> new Money("abc"));
    }

    @Test
    void 천원_미만의_값을_입력받은_경우_예외_발생() {
        assertThrows(InvalidPaymentException.class, () -> new Money("999"));
    }

    @Test
    void 십만원_초과의_값을_입력받은_경우_예외_발생() {
        assertThrows(InvalidPaymentException.class, () -> new Money("100001"));
    }

    @Test
    void 구매_가능한_로또_개수_반환() {
        Money money = new Money("4999");
        assertThat(money.getBuyableLottoNumber()).isEqualTo(4);
    }
}
