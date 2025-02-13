package lotto;

import static lotto.Lotto.MAX_LOTTO_NUMBER;
import static lotto.Lotto.MIN_LOTTO_NUMBER;
import static lotto.Lotto.validateLottoNumber;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또는 1과 45 사이의 번호가 아니면 예외를 던진다")
    @Test
    void 로또는_1과_45_사이의_번호가_아니면_예외를_던진다() {
        assertThatThrownBy(() -> validateLottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 %d ~ %d 사이여야 합니다.".formatted(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }
}
