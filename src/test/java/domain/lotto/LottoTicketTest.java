package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("돈이 0이하일 경우 로또 티켓 생성 예외처리 테스트")
    public void lotto_ticket_make_test() {
        assertThatThrownBy(() -> new LottoTicket(500))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[Error] 로또는 개당 1,000원 입니다.");
    }

}
