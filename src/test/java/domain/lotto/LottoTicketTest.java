package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_PURCHASE_PRICE_ERROR;
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
            .hasMessageContaining(LOTTO_PURCHASE_PRICE_ERROR.getMessage());
    }

}
