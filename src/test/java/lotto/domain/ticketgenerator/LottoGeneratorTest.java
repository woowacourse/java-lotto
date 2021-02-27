package lotto.domain.ticketgenerator;

import lotto.domain.ticketgenerator.strategy.AutoStrategy;
import lotto.domain.ticketgenerator.strategy.ManualStrategy;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
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
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        ));

        LottoTicket ticket2 = new LottoTicket(Arrays.asList(
                LottoNumber.of(31),
                LottoNumber.of(32),
                LottoNumber.of(33),
                LottoNumber.of(34),
                LottoNumber.of(35),
                LottoNumber.of(36)
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
