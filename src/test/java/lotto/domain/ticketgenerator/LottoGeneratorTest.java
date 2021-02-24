package lotto.domain.ticketgenerator;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.strategy.AutoStrategy;
import lotto.strategy.ManualStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @DisplayName("구입 금액에 따른 티켓 다발 생성")
    @Test
    void Should_ReturnPurchasedTickets_When_Purchased() {
        UserPurchase userPurchase = new UserPurchase(18000, 3);
        LottoGenerator lottoGenerator = new LottoGenerator();
        lottoGenerator.setGenerateStrategy(new AutoStrategy(15));
        LottoTickets lottoTickets = lottoGenerator.generateTickets();
        assertThat(lottoTickets.size()).isEqualTo(18);
    }


    private final LottoTicket lottoTicket = new LottoTicket(
        Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        )
    );

}
