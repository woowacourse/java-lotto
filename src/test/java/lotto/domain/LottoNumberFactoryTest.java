package lotto.domain;

import lotto.Exception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberFactoryTest {
    @Test
    void 숫자가1이상45이하인지() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            LottoNumberFactory.generateLottoNumbers();
        });
    }
}
