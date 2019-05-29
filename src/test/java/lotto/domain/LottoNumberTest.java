package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    public void 로또_번호_생성_테스트() {
        assertThat(LottoNumber.get(3)).isEqualTo(LottoNumber.get(3));
    }

    @Test
    public void 로또_범위에서_벗어난_번호로_생성시_예외처리_테스트() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.get(46);
        });
    }
}
