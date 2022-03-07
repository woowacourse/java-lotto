package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMoneyTest {

    @Test
    @DisplayName("음수의 금액으로 생성하면 에러를 던지는지 확인한다.")
    void checkNegativeMoney() {
        assertThatThrownBy(() -> new LottoMoney(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 양수로 입력해야 합니다.");
    }

    @Test
    @DisplayName("티켓 가격의 배수가 아닌 금액으로 로또 구입 금액을 생성할 시 에러를 던지는지 확인한다.")
    void checkNotMultiplesOfPrice() {
        assertThatThrownBy(() -> new LottoMoney(1700))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액을 1000의 배수로 입력해주세요.");
    }

    @Test
    @DisplayName("수동 구매 갯수를 입력받아서 금액을 차감할 때 잘 차감된 결과를 내는지 확인한다.")
    void checkPurchaseSelfTicket() {
        LottoMoney money = new LottoMoney(17000);
        LottoMoney change = money.purchaseSelfTicket(3);
        assertThat(change.getValue()).isEqualTo(14000);
    }

    @Test
    @DisplayName("수동 구매 갯수를 입력받아서 금액을 차감할 때 금액을 초과하는 구매 갯수를 입력하면 에러를 던지는지 확인한다.")
    void checkPurchaseSelfTicketError() {
        LottoMoney money = new LottoMoney(17000);
        assertThatThrownBy(() -> money.purchaseSelfTicket(18))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액보다 많은 수동 티켓을 구매할 수 없습니다.");
    }
}