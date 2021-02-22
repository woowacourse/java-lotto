package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    void 생성_및_비교() {
        // given, when
        LottoNumber lottoNumber1 = LottoNumber.valueOf(1);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(1);

        // then
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @DisplayName("로또 번호는 1부터 45사이의 값이어야 한다.")
    @Test
    void 유효성_검사() {
        assertThatIllegalArgumentException().isThrownBy(() -> LottoNumber.valueOf(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> LottoNumber.valueOf(46));
    }
}
