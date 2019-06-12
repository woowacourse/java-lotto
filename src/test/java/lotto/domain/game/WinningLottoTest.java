package lotto.main.game;

import lotto.domain.game.WinningLotto;
import lotto.domain.ticket.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {
    @Test
    public void 보너스_당첨_번호_겹침_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            WinningLotto.of(Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)), LottoNumber.of(6));
        });
    }
}