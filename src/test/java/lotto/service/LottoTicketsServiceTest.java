package lotto.service;

import lotto.domain.lottos.LottoTickets;
import lotto.domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.money.Money.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsServiceTest {

    @Test
    @DisplayName("돈을 주면 로또 티켓 여러장 만들어진다.")
    public void createLottoTicketsTest() {
        LottoTickets lottoTickets = LottoTicketsService.createLottoTickets(new Money("5000"));

        assertThat(lottoTickets).isInstanceOf(LottoTickets.class);
    }

    @Test
    @DisplayName("돈을 1000원 이하로 주면 로또 티켓 만들어주지 않는다.")
    public void notEnoughMoneyTest() {
        assertThatThrownBy(() -> {
            LottoTicketsService.createLottoTickets(new Money("500"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(SHORT_MONEY_MESSAGE, LOTTO_PRICE));
    }
}