package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void validateLottoNumbersDoesNotContainBonusNumber() {
        assertThrows(InvalidBonusBallException.class, () -> new WinningLotto(Arrays.asList(1,2,3,4,5,6), 1));
    }
}
