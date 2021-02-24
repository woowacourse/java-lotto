package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoPurchaseTest {
    @DisplayName("Budget에서 금액을 차감하고 로또를 자동 구매한다.")
    @Test
    public void purchaseLottoTicketTest() {
        Budget budget = new Budget(new Money(3_000));
        LottoTicket lottoTicket = LottoPurchase.buyAutomatically(budget);
    }
}
