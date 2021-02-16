package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;


class PurchasingPriceTest {

    @DisplayName("로또 구입 금액은 1000원 미만인 경우 객체 생성 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 999})
    void cannotMakePurchasingPrice(int purchasingPrice) {
        assertThatCode(() -> {
            new PurchasingPrice(purchasingPrice);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓 최소 구매 금액은 1000원입니다.");
    }

    @DisplayName("로또 구입 금액이 1000원 이상이면 객체가 정상 생성된다")
    @Test
    void makePurchasingPrice() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(1000);

        int value = purchasingPrice.getPurchasingPrice();

        assertThat(value).isEqualTo(1000);
    }

    @DisplayName("입력 금액을 통해 구매 가능한 로또 티켓 개수를 계산한다")
    @Test
    void calculateLottoTicketCounts() {
        int price = 3987;
        PurchasingPrice purchasingPrice = new PurchasingPrice(price);

        int purchasableLottoTicketCounts = purchasingPrice.calculateLottoTicketCounts();

        assertThat(purchasableLottoTicketCounts).isEqualTo(3987 / 1000);
    }
}