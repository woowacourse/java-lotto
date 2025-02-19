package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 지난주_당첨_번호가_생성된다() {
        final String numbers = "1, 2, 3, 4, 5, 6";
        final String bonusNumber = "7";

        assertThatNoException()
                .isThrownBy(() -> new WinningLotto(numbers, bonusNumber));
    }

    @Test
    void 번호_중_보너스_번호가_중복되어_예외가_발생한다() {
        final String numbers = "1, 2, 3, 4, 5, 6";
        final String bonusNumber = "6";

        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 번호가_중복되어_예외가_발생한다() {
        final String numbers = "1, 2, 3, 4, 6, 6";
        final String bonusNumber = "5";

        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
