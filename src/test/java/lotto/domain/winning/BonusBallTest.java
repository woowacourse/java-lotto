package lotto.domain.winning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BonusBallTest {
    @Test
    void null_check() {
        assertThrows(NullPointerException.class, () -> {
            BonusBall.createBonusBall(null, WinningLottoTest.actual);
        });
    }

    @Test
    void 당첨로또번호와의_중복확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            BonusBall.createBonusBall(1, WinningLottoTest.actual);
        });
    }
}