package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoTicketTest {
    @Test
    @DisplayName("AutoNumber 생성자 테스트")
    void AutoNumbers() {
        new LottoNumbers();
        AutoTicket an = new AutoTicket();
        assertThat(an.getAutoTicket().size()).isEqualTo(6);
    }
}
