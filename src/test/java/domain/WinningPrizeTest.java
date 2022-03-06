package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningPrizeTest {

    @Test
    @DisplayName("WinningPrize가 맞은 숫자 갯수랑 보너스 여부로 2등을 잘 판단하는지 확인한다.")
    void checkPrizeResultSecond() {
        assertThat(WinningPrize.findWinningPrize(5, true))
                .isEqualTo(WinningPrize.SECOND);
    }

    @Test
    @DisplayName("WinningPrize가 맞은 숫자 갯수랑 보너스 여부로 2등을 제외한 상금 결과를 잘 판단하는지 확인한다.")
    void checkPrizeResult() {
        assertThat(WinningPrize.findWinningPrize(5, false))
                .isEqualTo(WinningPrize.THIRD);
    }
}
