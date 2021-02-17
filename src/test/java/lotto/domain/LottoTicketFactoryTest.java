package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketFactoryTest {
    @Test
    @DisplayName("입력받은 금액만큼 티켓 생성")
    void createLottoTickets() {
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        assertThat(lottoTicketFactory.buyLottoTickets(new Money(10000)).size()).isEqualTo(10);
    }
}