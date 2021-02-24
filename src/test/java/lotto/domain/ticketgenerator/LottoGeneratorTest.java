package lotto.domain.ticketgenerator;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.strategy.AutoStrategy;
import lotto.strategy.ManualStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {
    private LottoGenerator lottoGenerator;
    private List<LottoTicket> tickets;

    @BeforeEach
    void setUp() {
        lottoGenerator = new LottoGenerator();
        LottoTicket ticket1 = new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        LottoTicket ticket2 = new LottoTicket(Arrays.asList(
                new LottoNumber(31),
                new LottoNumber(32),
                new LottoNumber(33),
                new LottoNumber(34),
                new LottoNumber(35),
                new LottoNumber(36)
        ));
        tickets = Arrays.asList(ticket1, ticket2);
    }

    @DisplayName("수동 티켓 생성 테스트")
    @Test
    void createManualTicketTest() {
        lottoGenerator.setGenerateStrategy(new ManualStrategy(tickets));
        assertThat(lottoGenerator.generateTickets().size()).isEqualTo(2);
    }

    @DisplayName("자동 티켓 생성 테스트")
    @Test
    void Should_ReturnPurchasedTickets_When_Purchased() {
        lottoGenerator.setGenerateStrategy(new AutoStrategy(15));
        LottoTickets lottoTickets = lottoGenerator.generateTickets();
        assertThat(lottoTickets.size()).isEqualTo(15);
    }
}
