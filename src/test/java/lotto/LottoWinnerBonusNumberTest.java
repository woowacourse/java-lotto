package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoWinnerBonusNumberTest {
    @Test
    @DisplayName("보너스 숫자를 생성한다.")
    public void createNumberTest() {
        LottoWinnerBonusNumber number = new LottoWinnerBonusNumber(5);
        assertThat(number).isEqualTo(new LottoWinnerBonusNumber(5));
    }

    @Test
    @DisplayName("보너스 로또 숫자는 1~45 사이의 숫자여야한다.")
    public void validateNumberTest() {
        assertThatThrownBy(() -> {
            new LottoWinnerBonusNumber(46);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
