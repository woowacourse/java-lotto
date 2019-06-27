package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {
    @Test
    void 로또번호가_보너스를_포함할경우() {
        assertThrows(DuplicatedNumberException.class, () ->
                WinningLotto.of(Arrays.asList(LottoNo.of(1), LottoNo.of(2), LottoNo.of(3),
                        LottoNo.of(4), LottoNo.of(5), LottoNo.of(6)), LottoNo.of(6)));
    }
}