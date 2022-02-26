package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {
    @DisplayName("1~45 사이의 숫자인지 검증한다.")
    @Test
    public void 번호가_범위에_맞는지_검증_0() {
        // given & when & then
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1~45 사이의 숫자인지 검증한다.")
    @Test
    public void 번호가_범위에_맞는지_검증_46() {
        // given & when & then
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }
}