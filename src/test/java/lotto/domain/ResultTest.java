package lotto.domain;

import lotto.util.LottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @DisplayName("매치 카운트랑 보너스 카운트에 맞는 합당한 결과를 알려 주는지")
    @Test
    void getResult() {
        int matchCount = 0;
        boolean bonusMatch = true;

        assertThat(Result.decisionLottoRank(matchCount, bonusMatch)).isEqualTo(Result.NONE);
    }

    @DisplayName("순위 계산 기능 확인")
    @Test
    void checkRank() {
        Result first = Result.decisionLottoRank(6, false);
        Result second = Result.decisionLottoRank(5, true);
        Result third = Result.decisionLottoRank(5, false);
        Result fourth = Result.decisionLottoRank(4, false);
        Result fifth = Result.decisionLottoRank(3, false);
        Result none = Result.decisionLottoRank(2, true);

        assertThat(first).isEqualTo(Result.FIRST);
        assertThat(second).isEqualTo(Result.SECOND);
        assertThat(third).isEqualTo(Result.THIRD);
        assertThat(fourth).isEqualTo(Result.FOURTH);
        assertThat(fifth).isEqualTo(Result.FIFTH);
        assertThat(none).isEqualTo(Result.NONE);
    }

    @DisplayName("총 수익을 계산")
    @Test
    void calculateTotalProfit() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40)); // FIFTH
        Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 20)); // SECOND
        Lotto lotto3 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 20)); // SECOND

        LottoFactory manualLotto = LottoFactory.of(Arrays.asList(lotto1, lotto2, lotto3));
        LottoFactory autoLotto = LottoFactory.of(0);

        WinningLotto winningLotto = new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(20));

        List<Result> results = winningLotto.getWinningResult(manualLotto, autoLotto);

        float profit = Result.calculateProfit(results);
        assertThat(profit).isEqualTo(60_005_000);
    }
}
