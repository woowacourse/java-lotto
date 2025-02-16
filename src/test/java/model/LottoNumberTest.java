package model;

import static constant.LottoConstant.MAX_LOTTO_NUMBER;
import static constant.LottoConstant.MIN_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    void 로또_번호는_1부터_45사이여야_한다() {
        LottoNumber lottoNumber = new LottoNumber(40);
        assertThat(lottoNumber.value()).isEqualTo(40);
    }

    @Test
    void 로또_번호가_1부터_45사이가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            new LottoNumber(46);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(String.format("로또 번호는 %d 이상 %d 이하여야 합니다.",
                MIN_LOTTO_NUMBER,
                MAX_LOTTO_NUMBER));
    }
}
