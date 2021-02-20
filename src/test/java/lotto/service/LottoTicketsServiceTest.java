package lotto.service;

import lotto.domain.lottos.LottoTickets;
import lotto.domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
            LottoTickets lottoTickets = LottoTicketsService.createLottoTickets(new Money("500"));
        });
    }
}