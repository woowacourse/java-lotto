package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {
    @Test
    void 로또_번호로_6개가_아닌_수가_들어왔을경우() {
        assertThrows(InvalidSizeException.class
                , () -> Lotto.of(Arrays.asList(LottoNo.of(1)
                        , LottoNo.of(2), LottoNo.of(10)
                        , LottoNo.of(15), LottoNo.of(22))));
    }

    @Test
    void 로또_번호로_같은_수가_들어왔을경우() {
        assertThrows(DuplicatedNumberException.class
                , () -> Lotto.of(Arrays.asList(LottoNo.of(1)
                        , LottoNo.of(2), LottoNo.of(10)
                        , LottoNo.of(15), LottoNo.of(22)
                        , LottoNo.of(22))));
    }

    @Test
    void 로또_인스턴스끼리_중복된_숫자의_갯수_테스트() {
        Lotto lotto1 = Lotto.of(Arrays.asList(LottoNo.of(1)
                , LottoNo.of(2), LottoNo.of(3)
                , LottoNo.of(4), LottoNo.of(5)
                , LottoNo.of(6)));
        List<LottoNo> lotto2 = Arrays.asList(LottoNo.of(1)
                , LottoNo.of(2), LottoNo.of(3)
                , LottoNo.of(15), LottoNo.of(22)
                , LottoNo.of(25));
        assertThat(lotto1.findCountOfMatchNo(lotto2)).isEqualTo(3);
    }
}