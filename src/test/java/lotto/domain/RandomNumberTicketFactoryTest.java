package lotto.domain;

import lotto.domain.LottoTicket;
import lotto.domain.RandomNumberTicketFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberTicketFactoryTest {

    @DisplayName("티켓 팩토리에서 생성한 티켓 넘버의 개수가 6개인지 확인")
    @Test
    void checkSizeOfTicketNumbers() {
        LottoTicket lottoTicket = RandomNumberTicketFactory.makeTicket();
        assertThat(lottoTicket.getLottoNumbers().size()).isEqualTo(6);
    }
}
