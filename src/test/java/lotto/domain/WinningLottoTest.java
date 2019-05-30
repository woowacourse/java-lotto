package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {
    @Test
    void 기존번호_보너스번호_중복() {
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        int bonusBall = 1;
        assertThrows(IllegalArgumentException.class, () -> {
            new WinningLotto(lottoTicket, bonusBall);
        });
    }

    @Test
    void 일등_테스트() {
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        int bonusBall = 7;
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusBall);
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(LottoRank.FIRST);
    }
}