package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WinningBoardTest {

    @DisplayName("맞춘 로또 번호 갯수와 보너스볼 맞춤 여부를 확인해 해당하는 보상을 반환한다.")
    @ParameterizedTest(name = "{index} => hits = {0}, hitBonus = {1}, winning = {2}")
    @MethodSource("testFreeParams")
    void checkBoard(int hits, boolean hitBonus, WinningBoard winning) {
        assertThat(WinningBoard.findWinnings(hits, hitBonus)).isEqualTo(winning);
    }

    private static Object[] testFreeParams() {
        return new Object[]{
            new Object[]{5, true, WinningBoard.SECOND},
            new Object[]{5, false, WinningBoard.THIRD},
            new Object[]{2, true, WinningBoard.ZERO},
            new Object[]{4, false, WinningBoard.FOURTH}
        };
    }
}
