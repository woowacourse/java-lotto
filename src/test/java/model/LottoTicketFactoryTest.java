package model;

import static model.LottoTicketFactory.NOT_ENOUGH_MONEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource(value = {"7500,7", "8000,8"})
    @DisplayName("천원 당 한 장의 로또를 구매할 수 있다.")
    void purchaseLotto(int money, int ticketCount) {
        // given

        // when
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(money);

        // then
        assertThat(lottoTickets.size()).isEqualTo(ticketCount);
    }
}
