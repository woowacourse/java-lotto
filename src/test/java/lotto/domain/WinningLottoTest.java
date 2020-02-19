package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
