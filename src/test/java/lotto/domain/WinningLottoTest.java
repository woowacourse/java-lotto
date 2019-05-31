package lotto.domain;

import lotto.exception.BonusBallValidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 보너스볼_중복_예외() {
        assertThrows(BonusBallValidException.class, () -> {
            new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.of(6));
        });
    }
}
