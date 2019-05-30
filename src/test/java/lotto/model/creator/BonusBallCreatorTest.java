package lotto.model.creator;

import lotto.model.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonusBallCreatorTest {
        @Test
        void 보너스볼_범위_검사() {
                assertThrows(InvalidLottoNumberException.class, () -> {
                        BonusBallCreator.create(55);
                });
        }
}
