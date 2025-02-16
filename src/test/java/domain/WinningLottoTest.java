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
}
