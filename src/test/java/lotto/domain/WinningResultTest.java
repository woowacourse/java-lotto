package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
    @Test
    @DisplayName("상금합 구하기")
    public void calculate() {
        List<Ranking> lottoNumbersList = new ArrayList<>();
        lottoNumbersList.add(Ranking.FIFTH_PLACE);
        lottoNumbersList.add(Ranking.FOURTH_PLACE);
        lottoNumbersList.add(Ranking.THIRD_PLACE);

        WinningResult winningResult = new WinningResult(lottoNumbersList);

        assertThat(winningResult.calculatePrizeSum()).isEqualTo(1555000);
    }
}
