package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    @DisplayName("로또 숫자는 값이 같다면 동일한 객체를 반환한다")
    void 로또_숫자는_값이_같다면_동일한_객체를_반환한다() {
        // given
        LottoNumber number1 = LottoNumber.from(LottoRule.MIN_LOTTO_NUMBER.getValue());
        LottoNumber number2 = LottoNumber.from(LottoRule.MIN_LOTTO_NUMBER.getValue());

        // when
        // then
        assertThat(number1)
                .isEqualTo(number2);
    }

    @Test
    @DisplayName("로또 숫자는 정해진 범위 사이여야 한다")
    void 로또_숫자는_정해진_범위가_아니면_예외가_발생한다() {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(LottoRule.MIN_LOTTO_NUMBER.getValue() - 1));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(LottoRule.MAX_LOTTO_NUMBER.getValue() + 1));
    }
}
