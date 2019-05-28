package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {
    @Test
    void 로또_번호로_6개가_아닌_수가_들어왔을경우() {
        assertThrows(InvalidNumberException.class
                , () -> Lotto.of(Arrays.asList(new LottoNo(1)
                        , new LottoNo(2), new LottoNo(10)
                        , new LottoNo(15), new LottoNo(22))));
    }

    @Test
    void 로또_번호로_같은_수가_들어왔을경우() {
        assertThrows(InvalidNumberException.class
                , () -> Lotto.of(Arrays.asList(new LottoNo(1)
                        , new LottoNo(2), new LottoNo(10)
                        , new LottoNo(15), new LottoNo(22)
                        , new LottoNo(22))));
    }
}