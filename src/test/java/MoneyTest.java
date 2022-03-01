import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009})
    @DisplayName("10 단위가 아니면 예외 발생")
    void moneyExceptionNotDividable10(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 10 단위로 나누어 떨어져야 합니다.");

    }

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 400, 700, 900, 950, 990})
    @DisplayName("최소 로또 구매 비용 미만 예외")
    void moneyUnderLottoTicketPriceException(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 1000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("돈 정상 생성")
    void moneyInsert() {
        assertDoesNotThrow(() -> new Money(1500));
    }

    @Test
    @DisplayName("수동 로또 구매시 보유 금액을 초과할 경우 예외")
    void manuallyPurchasedLottoPriceExceedException() {
        Money money = new Money(2000);

        assertThatThrownBy(() -> money.validateManualCount(5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구매하려는 로또가 보유 금액을 초과했습니다.");
    }
}
