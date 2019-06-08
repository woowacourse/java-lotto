package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @Test
    void create() {
        int amountOfCustom = 3;

        assertThat(new LottoTickets(amountOfCustom)).isEqualTo(new LottoTickets(amountOfCustom));
    }

    @Test
    void 입력해야할_수동로또가_더있는경우_테스트() {
        int amountOfCustom = 3;
        LottoTickets lottoTickets = new LottoTickets(amountOfCustom);

        assertThat(lottoTickets.needMoreCustomLottoTicket()).isTrue();
    }
}
