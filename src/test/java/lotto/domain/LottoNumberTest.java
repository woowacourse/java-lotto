package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @DisplayName("로또 번호 생성하기")
    @Test
    void create() {
        LottoNumber lottoNumber = new LottoNumber(3);
        assertThat(lottoNumber).isEqualTo(new LottoNumber(3));
    }

    @DisplayName("로또 번호는 1~45사이의 숫자이어야 한다.")
    @Test
    void checkNumberInProperRange() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoNumber(0));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoNumber(46));
    }

}
