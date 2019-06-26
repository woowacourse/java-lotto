package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 잘_못_생성되었을_때_테스트() {
        assertThrows(InvalidBonusLottoNumberException.class, () -> WinningLotto.generate(ManualLottoGenerator.create("1,2,3,4,5,6"), 123));
    }
}
