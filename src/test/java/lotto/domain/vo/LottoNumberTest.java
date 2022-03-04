package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {
    @DisplayName("1~45 사이의 숫자인지 검증한다.")
    @Test
    public void checkLottoNumberBoundaryWith0() {
        // given & when & then
        assertThatThrownBy(() -> LottoNumber.from(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1~45 사이의 숫자인지 검증한다.")
    @Test
    public void checkLottoNumberBoundaryWith46() {
        // given & when & then
        assertThatThrownBy(() -> LottoNumber.from(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("동일 인스턴스 검증")
    @Test
    public void checkSameInstance() {
        // given
        LottoNumber sameInstance1 = LottoNumber.from(1);
        LottoNumber sameInstance2 = LottoNumber.from(1);

        LottoNumber otherInstance3 = LottoNumber.from(2);

        // when & then
        assertThat(sameInstance1).isSameAs(sameInstance2);
        assertThat(sameInstance1).isNotSameAs(otherInstance3);
        assertThat(sameInstance2).isNotSameAs(otherInstance3);
    }
}