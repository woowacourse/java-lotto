package lotto.domain.ticketgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    @DisplayName("매개변수로 받은 개수만큼 로또 티켓 구입 테스트")
    @Test
    void Should_Return_LottoTicketsWithExactNumberOfTickets_When_Purchase() {

        UserPurchase userPurchase = new UserPurchase(new PurchasePrice(10_000), new LottoTickets());
        LottoGenerator lottoGenerator = new LottoGenerator();

        LottoTickets lottoTickets = lottoGenerator.purchaseTickets(userPurchase);

        assertThat(lottoTickets.getTickets().size()).isEqualTo(10);
    }
}
