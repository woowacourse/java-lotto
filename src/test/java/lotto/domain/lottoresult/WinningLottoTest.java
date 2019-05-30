package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    WinningLotto winningLotto = WinningLotto.create(Arrays.asList(1, 2, 3, 4, 5, 6));

    @Test
    void 다른문자_포함() {
        assertThrows(InvalidWinningLottoException.class, () -> {
            WinningLotto.create("1, a, 3,4, 5");
        });
    }

    @Test
    void 번호개수_부족() {
        assertThrows(InvalidWinningLottoException.class, () -> {
            WinningLotto.create("1, 3,5");
        });
    }

    @Test
    void 번호범위_벗어남() {
        assertThrows(InvalidWinningLottoException.class, () -> {
            WinningLotto.create("46, 1, 2, 3, 4, 5");
        });
    }

    @Test
    void 두개_이하_일치_로또() {
        LottoTicket lotto = LottoTicket.create(Arrays.asList(11,2,3,8,9,10));
        assertThat(winningLotto.checkLottoRank(lotto))
                .isEqualTo(LottoRank.rankOf(2));
    }

    @Test
    void 세개_일치_로또() {
        LottoTicket lotto = LottoTicket.create(Arrays.asList(1,2,3,8,9,10));
        assertThat(winningLotto.checkLottoRank(lotto))
                .isEqualTo(LottoRank.rankOf(3));
    }
}
