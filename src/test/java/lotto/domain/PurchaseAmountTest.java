package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    private static final int LOTTO_PRICE = 1000;

    @Test
    @DisplayName("구입 금액을 생성한다")
    void makePurchaseAmount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThat(purchaseAmount.getAmount()).isEqualTo(14_000);
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아닐 경우 예외를 발생시킨다.")
    void throwExceptionWhenInputLessThan1000() {
        assertThatThrownBy(() -> new PurchaseAmount(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입금액은 " + LOTTO_PRICE + "원 단위여야 합니다");
    }

//    @Test
//    @DisplayName("구입 금액만큼 티켓 개수를 반환한다")
//    void calcTicketAmount() {
//        PurchaseAmount purchaseAmount = new PurchaseAmount(14_500);
//
//        assertThat(purchaseAmount.calculateTheNumberOfTicket()).isEqualTo(14);
//    }

    @ParameterizedTest
    @CsvSource({"0, 14", "3, 11", "14, 0"})
    @DisplayName("수동 로또 구매 후 남은 돈으로 살 수 있는 자동 로또 개수를 반환한다.")
    void calculateAutoLottoCount(int count, int expected) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThat(purchaseAmount.calculateAutoLottoCount(count)).isEqualTo(expected);
    }
}
