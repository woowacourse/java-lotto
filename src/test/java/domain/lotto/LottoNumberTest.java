package domain.lotto;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 숫자가 잘 생성되는지 테스트 한다.")
    void lottoNumber_make_test() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 숫자 번호 범위는 1부터 45다.")
    public void lottoNumber_range_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                new LottoNumber(46);
            });
    }
}
