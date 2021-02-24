package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoPurchaseTest {
    @DisplayName("금액이 입력되면 금액에 맞게 로또 티켓을 구매한다.")
    @Test
    public void purchaseLottoTicketTest() {
        Money money = new Money(3_000);
        List<LottoTicket> lottoTickets = LottoPurchase.buy(money);
        assertThat(lottoTickets.size()).isEqualTo(3);
    }
}
