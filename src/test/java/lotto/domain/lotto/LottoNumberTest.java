package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoNumberTest {
    @Test
    void 생성자_생성() {
        for (int i = LottoNumber.MIN_BOUNDARY; i <= LottoNumber.MAX_BOUNDARY; i++) {
            assertThat(LottoNumber.getNumber(i).toString()).isEqualTo(String.valueOf(i));
        }
    }

    @Test
    void 생성자_오류_범위를_벗어남() {
        assertThatThrownBy(() -> LottoNumber.getNumber(0)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> LottoNumber.getNumber(46)).isInstanceOf(NullPointerException.class);
    }
}
