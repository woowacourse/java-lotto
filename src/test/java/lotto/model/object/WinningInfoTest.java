package lotto.model.object;

import lotto.model.creator.BonusBallCreator;
import lotto.model.creator.LottoCreator;
import lotto.model.creator.WinningInfoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningInfoTest {
        WinningInfo winningInfo;

        @BeforeEach
        void setUp() {
                winningInfo = WinningInfoCreator.create(LottoCreator.create(new String[]{"1", "2", "3", "4", "5", "6"}), BonusBallCreator.create(7));

        }

        @Test
        void 일치_숫자_추출_검사() {
                assertThat(winningInfo.getMatchNumber(LottoCreator.create(new String[]{"2", "4", "6", "8", "10", "12"}))).isEqualTo(3);
        }

        @Test
        void 보너스볼_존재_검사() {
                assertThat(winningInfo.hasBonusBallIn(LottoCreator.create(new String[]{"2", "4", "7", "8", "10", "12"}))).isEqualTo(true);
        }
}