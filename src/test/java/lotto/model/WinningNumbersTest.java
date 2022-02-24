package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {

    @Test
    @DisplayName("WinningNumbers가 LottoNumber를 포함하는지 확인한다")
    void containsTest() {
        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(6);

        assertThat(winningNumbers.contains(lottoNumber)).isTrue();
    }
}
