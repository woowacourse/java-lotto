package lotto.view;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoseller.LottoSeller;
import lotto.domain.lottoticket.LottoTicket;
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

    @Test
    void 보너스_볼을_입력하면_LottoNumber를_반환하는_메소드() {
        LottoTicket winningLotto = InputView.makeWinningLotto("1, 2, 3, 4, 5, 6");
        assertThat(InputView.makeBonusBall("7", winningLotto)).isInstanceOf(LottoNumber.class);
    }
}