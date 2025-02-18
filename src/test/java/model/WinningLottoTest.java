package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {
    @DisplayName("보너스 볼이 1 이상 45 이하의 정수인지 검사한다")
    @Test
    void bonusBoundaryTest() {
        Assertions.assertThatCode(
                        () -> new WinningLotto(List.of(1, 2, 3, 4, 15, 45), 5))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 볼이 1 이상 45 이하의 정수가 아니라면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    void bonusBoundaryExceptionTest(int bonus) {
        Assertions.assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 15, 45), bonus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 볼이 당첨 번호와 중복된다면 예외가 발생한다")
    @Test
    void bonusDuplicateWithWinningNumberTest() {
        Assertions.assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 15, 45), 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("lotto가 bonus번호가 없으면 false를 반환한다")
    @Test
    void isBonusMatchTest() {
        WinningLotto winningLotto = new WinningLotto(List.of(2, 3, 4, 15, 44, 45), 1);
        Lotto falseLotto = new Lotto(Set.of(10, 2, 3, 4, 5, 6));
        org.junit.jupiter.api.Assertions.assertFalse(winningLotto.isBonusMatch(falseLotto));
    }

    @DisplayName("lotto가 bonus번호를 가지고 있으면 true를 반환한다")
    @Test
    void isBonusNotMatchTest() {
        WinningLotto winningLotto = new WinningLotto(List.of(2, 3, 4, 15, 44, 45), 1);
        Lotto trueLotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        org.junit.jupiter.api.Assertions.assertTrue(winningLotto.isBonusMatch(trueLotto));
    }
}