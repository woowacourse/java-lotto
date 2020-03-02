package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualCountTest {

    @Test
    @DisplayName("입력이 숫자인지 판단하는 테스트")
    void Should_Exception_isNotNumber() {
        assertThatThrownBy(() -> {
            new ManualCount("q", 1);
        }).isInstanceOf(NotNumberException.class)
            .hasMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("최대값이 지불한 돈보다 큰지 판단하는 테스트")
    void Should_Exception_isSmallerThanPayment() {
        assertThatThrownBy(() -> {
            Money money = new Money("10000");
            ManualCount manualCount = new ManualCount("11",
                money.getTicketCount() / Money.PAYMENT_UNIT);
        }).isInstanceOf(OverRangeException.class)
            .hasMessage("수동으로 구매할 수 있는 수가 너무 큽니다.");
    }
}
