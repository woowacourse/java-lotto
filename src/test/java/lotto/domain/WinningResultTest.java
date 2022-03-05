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
        // given - 준비
        List<Ranking> lottoNumbersList = new ArrayList<>();
        lottoNumbersList.add(Ranking.FIFTH);
        lottoNumbersList.add(Ranking.FIFTH);
        lottoNumbersList.add(Ranking.FOURTH);

        WinningResult winningResult = new WinningResult(lottoNumbersList);
        int expect = Ranking.FIFTH.getPrize() * 2 + Ranking.FOURTH.getPrize();

        // when - 테스트 행위
        long sum = winningResult.calculatePrizeSum();

        // then - 검증
        assertThat(sum).isEqualTo(expect);
    }
}
