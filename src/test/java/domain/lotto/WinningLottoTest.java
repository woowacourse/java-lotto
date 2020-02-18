package domain.lotto;

import static domain.lotto.LottoNumberTest.*;
import static domain.lotto.LottoTicketTest.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    void testWinningLotto() {
        LottoTicket lottoTicket = getLottoTicketFixture();
        LottoNumber bonusNumber = getLottoNumberSevenFixture();
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);
        assertThat(winningLotto);
    }

    @Test
    void createWinningLottoThrowException() {
        LottoTicket lottoTicket = getLottoTicketFixture();
        LottoNumber bonusNumber = getLottoNumberOneFixture();
        assertThatThrownBy(() -> new WinningLotto(lottoTicket, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
