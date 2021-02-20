package lottogame.domain.lotto;

import lottogame.utils.DuplicateLottoNumberException;
import lottogame.utils.InvalidBonusBallNumberException;
import lottogame.utils.InvalidLottoNumberRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("보너스 볼이 로또 번호와 중복되는 경우")
    @Test
    void 로또_번호_입력() {
        assertThatThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 6);
        }).isInstanceOf(DuplicateLottoNumberException.class);
    }

    @DisplayName("당첨 로또 번호가 범위를 벗어난 경우 예외 처리")
    @Test
    void 범위를_벗어난_당첨_로또_입력() {
        assertThatThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 2, 3, 4, 6, 59), 7);
        }).isInstanceOf(InvalidLottoNumberRangeException.class);
    }

    @DisplayName("당첨 보너스 볼 번호가 범위를 벗어난 경우 예외 처리")
    @Test
    void 범위를_벗어난_보너스_볼_입력() {
        assertThatThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 49);
        }).isInstanceOf(InvalidBonusBallNumberException.class);
    }
}
