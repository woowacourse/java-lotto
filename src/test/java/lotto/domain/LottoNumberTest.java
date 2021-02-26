package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoNumberTest {

    @DisplayName("1~45까지의 로또 번호는 같은 객체이다.")
    @Test
    void same() {
        assertThat(LottoNumber.from(1)).isSameAs(LottoNumber.from(1));
    }

    @DisplayName("로또 번호 생성하기")
    @Test
    void create() {
        LottoNumber lottoNumber = LottoNumber.from(3);
        assertThat(lottoNumber).isEqualTo(LottoNumber.from(3));
    }

    @DisplayName("로또 번호는 1~45사이의 숫자여야합니다.")
    @Test
    void checkNumberInProperRange() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumber.from(0));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumber.from(46));
    }
}
