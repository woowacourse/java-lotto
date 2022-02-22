package model;

import static model.LottoTicketFactory.NOT_ENOUGH_MONEY;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketFactoryTest {
    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.getInstance();

    @Test
    @DisplayName("로또는 최소 천원이 있어야 구매할 수 있다.")
    void underLottoTicketPrice() {
        // given
        final int notEnoughMoney = 999;

        // when

        // then
        assertThatThrownBy(() -> lottoTicketFactory.createTickets(notEnoughMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ENOUGH_MONEY);
    }

    @Test
    @DisplayName("천원 당 한 장의 로또를 구매할 수 있다.")
    void purchaseLotto() {
        // given
        final int money = 7500;

        // when
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(money);

        // then
        assertThat(lottoTickets.size()).isEqualTo(7);
    }
}
