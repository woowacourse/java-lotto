package lotto.domain.ticketgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    @DisplayName("매개변수로 받은 개수만큼 로또 티켓 구입 테스트")
    @Test
    void Should_Return_PurchasedLottoTicketsWithExactNumberOfTickets_When_Purchase() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        int numberOfTickets = 5;

        PurchasedLottoTickets purchasedLottoTickets
            = lottoGenerator.generatePurchasedTickets(numberOfTickets);

        assertThat(purchasedLottoTickets.getTickets().size()).isEqualTo(numberOfTickets);
    }
}
