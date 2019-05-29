package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
}