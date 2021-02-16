package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @DisplayName("숫자는 1이상 45이하여야 한다.")
    @Test
    public void correctNumberRange(){
        assertThatIllegalArgumentException()
            .isThrownBy(()-> new LottoNumber(50));

        assertThatIllegalArgumentException()
            .isThrownBy(()-> new LottoNumber(0));
    }

    @DisplayName("로또 번호 생성하기")
    @Test
    public void createLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber).isEqualTo(new LottoNumber(1));
    }
}