package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningResultTest {
    @DisplayName("당첨 상태별 개수를 저장한다")
    @Test
    void saveCountByWinningStatus() {
        WinningResult winningResult = new WinningResult();
        winningResult.update(WinningStatus.FIRST);

        assertThat(winningResult.getWinningResults().get(WinningStatus.FIRST)).isEqualTo(1);
        assertThat(winningResult.getWinningResults().get(WinningStatus.SECOND)).isEqualTo(0);
    }

    @DisplayName("수익률을 구한다")
    @ParameterizedTest
    @CsvSource(value = {"SECOND:10000:3000", "FOURTH:100000:0.5", "NONE:5000:0", "FIRST:1000:2000000"}, delimiter = ':')
    void calculateEarningRate(WinningStatus winningStatus, String purchaseInput, double expectedEarningRate) {
        WinningResult winningResult = new WinningResult();
        winningResult.update(winningStatus);

        assertThat(winningResult.calculateEarningRate(new Purchase(purchaseInput))).isEqualTo(expectedEarningRate);
    }
}
