package model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {
    @DisplayName("당첨 상태별 개수를 저장한다")
    @Test
    void saveCountByWinningStatus() {
        WinningResult winningResult = new WinningResult();
        winningResult.update(WinningStatus.FIRST);

        assertThat(winningResult.getWinningResults().get(WinningStatus.FIRST)).isEqualTo(1);
        assertThat(winningResult.getWinningResults().get(WinningStatus.SECOND)).isEqualTo(0);
    }

}