package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @Test
    @DisplayName("보너스볼 범위 유효성 검사")
    void validateBonusNumberScopeTest() {
        assertThatThrownBy(() -> WinningLotto.validateBonusNumberScope(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> WinningLotto.validateBonusNumberScope(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스볼 중복 확인")
    void validateBonusNumberDuplicationTest() {
        assertThatThrownBy(() -> new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스볼 일치 여부 확인")
    void isBonusNumberContainTest() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 10), 6);

        assertThat(winningLotto.isBonusNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)))).isTrue();
        assertThat(winningLotto.isBonusNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)))).isFalse();
    }
}
