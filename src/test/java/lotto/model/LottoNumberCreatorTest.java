package lotto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberCreatorTest {
        @Test
        void 로또_숫자_범위_검사_0() {
                assertThrows(InvalidLottoNumberException.class, () -> {
                        LottoNumberCreator.create(0);
                });
        }

        @Test
        void 로또_숫자_범위_검사_46() {
                assertThrows(InvalidLottoNumberException.class, () -> {
                        LottoNumberCreator.create(46);
                });
        }
}
