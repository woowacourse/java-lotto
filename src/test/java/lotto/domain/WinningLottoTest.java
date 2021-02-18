package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("6개의 당첨 번호는 서로 다른 번호여야한다.")
    @Test
    void 중복된_당첨_번호_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 1, 2, 3, 4, 5);
        int bonusNumber = 5;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("당첨 번호는 6개로 구성되어있어야한다.")
    @Test
    void 당첨_번호_길이_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5);
        int bonusNumber = 5;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("보너스 숫자는는 1부터 45사이의 번호여야한다.")
    @Test
    void 보너스_숫자_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = -1;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }
}
