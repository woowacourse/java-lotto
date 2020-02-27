package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTicketsGeneratorTest {
    @DisplayName("사용자가 구매한 금액만틈 LottoTicket 이 생성되는지 테스트")
    @Test
    void generateLottoTicketsTest() {
        Money money = new Money(14000);
        List<LottoTicket> lottoTickets = LottoTicketsGenerator.generateLottoTickets(money.getLottoTicketCount());
        Assertions.assertThat(lottoTickets.size()).isEqualTo(money.getLottoTicketCount());
    }
}
