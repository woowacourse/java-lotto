package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    private List<Lotto> lottos;
    private WinningNumbers winningNumbers;
    private BonusNumber bonusNumber;
    private Map<MatchResult, Integer> expectedMatchResults;

    @BeforeEach
    void setUpLottos() {
        lottos = new ArrayList<>();
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 45)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 44, 45)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 43, 44, 45)));

        winningNumbers = new WinningNumbers("1,2,3,4,5,7");
        bonusNumber = new BonusNumber("6");

        expectedMatchResults = new HashMap<>();
        expectedMatchResults.put(MatchResult.THREE_MATCH, 1);
        expectedMatchResults.put(MatchResult.FOUR_MATCH, 1);
        expectedMatchResults.put(MatchResult.FIVE_MATCH, 1);
        expectedMatchResults.put(MatchResult.FIVE_MATCH_WITH_BONUS_BALL, 1);
        expectedMatchResults.put(MatchResult.SIX_MATCH, 1);
    }

    @DisplayName("로또 한 줄과 당첨번호를 비교했을때 올바른 당첨 결과를 반환하는지 확인")
    @Test
    void findMatchResultTest() {
        assertThat(lottos.get(0).findMatchResult(winningNumbers, bonusNumber)).isEqualTo(MatchResult.SIX_MATCH);
        assertThat(lottos.get(1).findMatchResult(winningNumbers, bonusNumber)).isEqualTo(MatchResult.FIVE_MATCH_WITH_BONUS_BALL);
        assertThat(lottos.get(2).findMatchResult(winningNumbers, bonusNumber)).isEqualTo(MatchResult.FIVE_MATCH);
        assertThat(lottos.get(3).findMatchResult(winningNumbers, bonusNumber)).isEqualTo(MatchResult.FOUR_MATCH);
        assertThat(lottos.get(4).findMatchResult(winningNumbers, bonusNumber)).isEqualTo(MatchResult.THREE_MATCH);
    }

    @DisplayName("생성된 로또들과 당첨번호를 비교했을 때 올바른 당첨 결과를 반환하는지 확인")
    @Test
    void createMatchResultsTest() {
        Lottos lottos = new Lottos(this.lottos);
        Map<MatchResult, Integer> matchResults = lottos.createMatchResults(winningNumbers, bonusNumber);
        assertThat(matchResults).isEqualTo(expectedMatchResults);
    }

    @DisplayName("올바른 수익률을 반환하는지 확인")
    @Test
    void calculateEarningRateTest() {
        LottoResults lottoResults = new LottoResults(expectedMatchResults);
        int earningRate = lottoResults.calculateEarningRate(new PurchasePrice("5000"));
        assertThat(earningRate).isEqualTo(40_631_100);
    }
}
