package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exceptions.ExceptionMessage;
import lotto.exceptions.PurchaseAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @DisplayName("단위에 맞지 않는 구매 금액이 입력될 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, Lotto.LOTTO_PRICE - 1})
    void 단위에_맞지_않는_구매_금액이_입력될_경우_예외가_발생한다(int invalidAmount) {
        String messageTemplate = ExceptionMessage.INVALID_PURCHASE_AMOUNT.getContent();
        String expectedMessage = String.format(messageTemplate, Lotto.LOTTO_PRICE);

        assertThatThrownBy(() -> new PurchaseAmount(invalidAmount))
                .isInstanceOf(PurchaseAmountException.class)
                .hasMessage(expectedMessage);
    }
}