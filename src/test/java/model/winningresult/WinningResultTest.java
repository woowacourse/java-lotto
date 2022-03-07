package model.winningresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import model.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Test
    @DisplayName("당첨결과를 생성해서 저장한다.")
    void generateWinningResult() {
        final Map<Rank, Integer> result = new LinkedHashMap<>();
        result.put(Rank.FIRST, 0);
        result.put(Rank.SECOND, 0);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 1);
        result.put(Rank.FIFTH, 3);

        final WinningResult winningResult = new WinningResult(result);
        winningResult.getValue()
                .forEach((rank, count) -> assertThat(count)
                        .isEqualTo(result.get(rank)));
    }

    @Test
    @DisplayName("수익률 계산이 당첨결과와 일치하게 되는지 확인한다.")
    void getRateOfReturn() {
        final Map<Rank, Integer> result = new LinkedHashMap<>();
        result.put(Rank.FIRST, 0);
        result.put(Rank.SECOND, 0);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 1);
        result.put(Rank.FIFTH, 3);

        final WinningResult winningResult = new WinningResult(result);
        final int totalPurchaseLottoCount = 100;
        assertThat(winningResult.getRateOfReturn(totalPurchaseLottoCount)).isEqualTo(65000 / (double) 100000);
    }
}