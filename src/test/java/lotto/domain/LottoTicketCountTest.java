package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketCountTest {

    @Test
    @DisplayName("구입금액과 수동 개수에 따라 객체 생성")
    void LottoTicketCount() {
        LottoTicketCount count = new LottoTicketCount(5000, 3);
        assertThat(count.getAutoCount()).isEqualTo(2);
        assertThat(count.getManualCount()).isEqualTo(3);
    }
}
