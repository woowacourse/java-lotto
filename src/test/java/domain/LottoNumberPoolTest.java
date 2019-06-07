package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberPoolTest {
    @Test
    void 입력받은_숫자에_해당하는_LottoNumber를_제대로_되돌려주는지_테스트() {
        LottoNumber expectedLottoNumber = new LottoNumber(5);

        assertThat(LottoNumberPool.pickLottoNumber(5)).isEqualTo(expectedLottoNumber);
    }

    @Test
    void 입력받은_개수만큼_LottoNumber를_뽑아주는지_테스트() {
        assertThat(LottoNumberPool.randomPickAsManyAs(6).size()).isEqualTo(6);
    }

    @Test
    void 입력받은_개수가_로또번호_개수보다_크면_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumberPool.randomPickAsManyAs(46));
    }
}
