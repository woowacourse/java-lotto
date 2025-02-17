package model.draw;

import static org.assertj.core.api.Assertions.*;

import model.purchase.Purchase;
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
    @CsvSource(value = {"30_000_000:10_000:3_000", "50_000:100_000:0.5", "0:5_000:0", "2_000_000_000:1000:2_000_000"}
            , delimiter = ':')
    void calculateEarningRate(int totalPriceInput, int purchaseInput, double expectedEarningRate) {
        WinningResult winningResult = new WinningResult();

        assertThat(winningResult.calculateEarningRate(new Purchase(purchaseInput), () -> totalPriceInput))
                .isEqualTo(expectedEarningRate);
    }
}
