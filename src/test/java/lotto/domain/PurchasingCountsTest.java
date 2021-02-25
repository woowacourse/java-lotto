package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchasingCountsTest {

    @DisplayName("구매 개수 객체 생성 확인")
    @Test
    void makePurchasingCount() {
        Money money = new Money(5000);

        PurchasingCounts purchasingCounts = PurchasingCounts.of(money, 2);

        assertThat(purchasingCounts.getManualTicketCounts()).isEqualTo(2);
        assertThat(purchasingCounts.getAutoTicketCounts()).isEqualTo(3);
    }

    @DisplayName("수동 구매 개수 범위를 벗어났을 경우")
    @Test
    void cannotMakePurchasingCount() {
        Money money = new Money(5000);

        assertThatThrownBy(() -> {
            PurchasingCounts.of(money, 6);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 가능한 티켓 개수 범위를 벗어났습니다.");
    }
}