package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {
    @Test
    void 로또번호가_보너스를_포함할경우() {
        assertThrows(InvalidNumberException.class, () ->
                WinningLotto.of(Arrays.asList(new LottoNo(1), new LottoNo(2), new LottoNo(3),
                        new LottoNo(4), new LottoNo(5), new LottoNo(6)), new LottoNo(6)));
    }
}
