package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    @DisplayName("당첨 로또 생성 성공")
    @Test
    void winningLottoCreation() {

        final Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        final int bonusNumber = 7;

        assertThatCode(() -> WinningLotto.of(lotto, bonusNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호와 당첨 번호 중복 시 예외 발생")
    @Test
    void duplicateBonusNumberCreationThrowException() {

        final Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        final int bonusNumber = 4;

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

}