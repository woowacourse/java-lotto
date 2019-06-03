package lotto.view;

import lotto.domain.LottoSeller;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {
    @Test
    void 가격을_입력하면_LottoSeller를_반환하는_메소드() {
        assertThat(InputView.makeLottoSeller("1000")).isInstanceOf(LottoSeller.class);
    }

    @Test
    void 당첨_번호를_입력하면_LottoTicket을_반환하는_메소드() {
        assertThat(InputView.makeWinningLotto("1, 2, 3, 4, 5, 6")).isInstanceOf(LottoTicket.class);
    }
}