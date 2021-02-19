package lotto.domain.ticketgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    @DisplayName("매개변수로 받은 개수만큼 로또 티켓 구입 테스트")
    @Test
    void Should_Return_PurchasedLottoTicketsWithExactNumberOfTickets_When_Purchase() {
        UserPurchase userPurchase = new UserPurchase(10_000);
        LottoGenerator lottoGenerator = new LottoGenerator();

        PurchasedLottoTickets purchasedLottoTickets
            = lottoGenerator.generatePurchasedTickets(userPurchase);

        assertThat(purchasedLottoTickets.getTickets().size()).isEqualTo(10);
    }
}
