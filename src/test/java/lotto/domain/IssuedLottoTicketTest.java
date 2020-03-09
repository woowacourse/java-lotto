package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IssuedLottoTicketTest {

    @Test
    @DisplayName("구매한 개수만큼 로또티켓 생성")
    void IssuedLottoTicket() {
        LottoFactory.getInstance();
        IssuedLottoTicket issuedLottoTicket = new IssuedLottoTicket(5, 0);
        assertThat(issuedLottoTicket.getIssuedLottoTicket().size()).isEqualTo(5);
    }

}
