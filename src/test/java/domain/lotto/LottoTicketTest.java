package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("돈이 부족할 경우 로또 티켓 생성 예외처리 테스트")
    public void lotto_ticket_make_test() {
        assertThatThrownBy(() -> new LottoTicket(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[Error] 로또 구입 금액은 1,000원 이상 입니다.(로또 1개 당 1,000원)");
    }

}
