package model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningRankTest {

    @Test
    @DisplayName("순위를 정상적으로 선정하는지 확인한다.")
    void selectRank_Test() {
        final int matchCount = 5;
        final boolean hasBonusNumber = true;

        assertThat(WinningRank.valueOf(matchCount, hasBonusNumber)).isEqualTo(WinningRank.SECOND);
    }
}