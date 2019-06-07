package lotto.domain.lottoresult;

import lotto.domain.lotto.InvalidLottoNumberException;
import lotto.domain.lotto.LottoStrategy.ManualLottoStrategy;
import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 숫자아닌_보너스번호() {
        assertThrows(InvalidWinningLottoException.class, () -> {
            WinningLotto.create("1, 2, 3, 4, 5, 6", "a");
        });
    }

    @Test
    void 범위벗어난_보너스번호() {
        assertThrows(InvalidWinningLottoException.class, () -> {
            WinningLotto.create("1, 2, 3, 4, 5, 6", "46");
        });
    }

    @Test
    void 중복된_보너스번호() {
        assertThrows(InvalidWinningLottoException.class, () -> {
            WinningLotto.create("1, 2, 3, 4, 5, 6", "1");
        });
    }

    @Test
    void 두개_이하_일치_로또() {
        WinningLotto winningLotto = WinningLotto.create("1, 2, 3, 4, 5, 6", "12");
        LottoTicket lotto = LottoTicket.create(new ManualLottoStrategy("11, 2, 3, 8, 9, 10"));
        assertThat(winningLotto.checkLottoRank(lotto))
                .isEqualTo(LottoRank.FAIL);
    }

    @Test
    void 세개_일치_로또() {
        WinningLotto winningLotto = WinningLotto.create("1, 2, 3, 4, 5, 6", "12");
        LottoTicket lotto = LottoTicket.create(new ManualLottoStrategy("1, 2, 3, 8, 9, 10"));
        assertThat(winningLotto.checkLottoRank(lotto))
                .isEqualTo(LottoRank.FIFTH);
    }
}
