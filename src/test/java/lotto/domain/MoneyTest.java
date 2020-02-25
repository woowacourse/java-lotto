package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("로또 티켓수 반환 테스트")
    void give_lotto_ticket_test() {
        Money money = new Money("1500");
        assertThat(money.lottoTicketCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("거스름돈 반환 테스트")
    void give_change_money_test() {
        Money money = new Money("1500");
        assertThat(money.giveChangeMoney()).isEqualTo(500);
    }

    @Test
    @DisplayName("구입금액이 숫자가 아닐 경우 테스트")
    void not_number_test() {
        String purchaseAmount = "1a가A";
        assertThatThrownBy(() -> new Money(purchaseAmount))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("숫자가 아닙니다. 재입력 해주세요.");
    }

    @Test
    @DisplayName("구입금액이 음수일 경우 테스트")
    void negative_number_test() {
        String purchaseAmount = "-3";
        assertThatThrownBy(() -> new Money(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수입니다. 재입력 해주세요.");
    }

    @Test
    @DisplayName("천원 미안의 값이 구입금액으로 들어왔을때 반환 테스트")
    void under_one_thousand_won() {
        String purchaseAmountInput = "999";
        assertThatThrownBy(() -> new Money(purchaseAmountInput)).isInstanceOf(IllegalArgumentException.class)
        .hasMessage(String.format("한개도 구매할 수 없습니다. %s원을 반환합니다.",purchaseAmountInput));
    }
}
