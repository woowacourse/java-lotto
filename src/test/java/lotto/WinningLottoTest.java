package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {
    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외를 던진다")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @ParameterizedTest
    void 당첨_번호와_보너스_번호가_중복되면_예외를_던진다(int bonusNumber) {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
    }
}
