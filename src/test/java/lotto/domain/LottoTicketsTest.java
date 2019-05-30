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
    void 수동로또를_더만들어야하는지_메시지_확인() {
        LottoTickets lottoTickets = new LottoTickets(3);
        assertThat(lottoTickets.needMoreCustomLotto()).isTrue();
    }
}
