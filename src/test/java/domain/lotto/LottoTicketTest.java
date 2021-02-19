package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_PURCHASE_PRICE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("돈이 부족할 경우 로또 티켓 생성시 예외가 발생한다.")
    public void testLottoTicketMakeWhenLackOfMoney() {
        assertThatThrownBy(() -> new LottoTicket(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_PURCHASE_PRICE_ERROR.getMessage());
    }

}
