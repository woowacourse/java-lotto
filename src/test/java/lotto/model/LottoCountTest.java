package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {

    @Test
    @DisplayName("입력이 숫자인지 판단하는 테스트")
    void Should_Exception_isNotNumber() {
        assertThatThrownBy(() -> {
            new LottoCount(1, "q");
        }).isInstanceOf(NotNumberException.class)
            .hasMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("최대값이 지불한 돈보다 큰지 판단하는 테스트")
    void Should_Exception_isSmallerThanPayment() {
        assertThatThrownBy(() -> {
            Money money = new Money("10000");
            LottoCount lottoCount = new LottoCount(
                money.getTotalTicketCount(), "11");
        }).isInstanceOf(OverRangeException.class)
            .hasMessage("범위를 초과하였습니다.");
    }
}
