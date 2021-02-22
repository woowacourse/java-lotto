package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.LottoNumber.NUMBER_OUT_OF_BOUNDS_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {
    @DisplayName("로또 번호가 1에서 45 사이인지 검증 하는지")
    @Test
    void LottoNumber_numberOutOfBounds_throwError() {
        assertThatThrownBy(() -> new LottoNumber(0)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_OUT_OF_BOUNDS_ERROR);
        assertThatThrownBy(() -> new LottoNumber(46)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_OUT_OF_BOUNDS_ERROR);
    }

    @DisplayName("매칭이 되는 번호가 있는지 없는지에 대해 판별을 올바르게 하는지")
    @Test
    void hasAnyMatchingNumber() {

        List<LottoNumber> lottoNumbers = new ManualLottoGenerator(new int[]{1, 2, 3, 4, 5, 6}).createLottoNumbers();
        LottoNumber lottoNumber1 = new LottoNumber(4);
        LottoNumber lottoNumber2 = new LottoNumber(10);

        assertThat(lottoNumber1.hasAnyMatchingNumber(lottoNumbers)).isTrue();
        assertThat(lottoNumber2.hasAnyMatchingNumber(lottoNumbers)).isFalse();
    }
}
