package lotto.domain;

import static lotto.domain.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MIN_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @DisplayName("로또 번호가 1과 45 사이의 숫자가 아니면 예외를 던진다")
    @ValueSource(ints = {0, 46, 100})
    @ParameterizedTest
    void 번호가_1과_45_사이의_번호가_아니면_예외를_던진다(int lottoNumber) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new LottoNumber(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 %d ~ %d 사이여야 합니다.".formatted(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }
}
