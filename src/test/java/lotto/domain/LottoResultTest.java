package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    private static Lottos lottos;
    private static WinningBalls winningBalls;
    private static BonusBall bonusBall;
    private static Map<MatchResult, Integer> expectedMatchResults;

    private static Stream<Arguments> lottosProvider() {
        return Stream.of(
                Arguments.of(lottos.getLottos().get(0), MatchResult.SIX_MATCH),
                Arguments.of(lottos.getLottos().get(1), MatchResult.FIVE_MATCH_WITH_BONUS_BALL),
                Arguments.of(lottos.getLottos().get(2), MatchResult.FIVE_MATCH),
                Arguments.of(lottos.getLottos().get(3), MatchResult.FOUR_MATCH),
                Arguments.of(lottos.getLottos().get(4), MatchResult.THREE_MATCH)
        );
    }

    @BeforeEach
    void setUp() {
        List<Ball> balls1 = Arrays.asList(
                new Ball(1), new Ball(2), new Ball(3), new Ball(4), new Ball(5), new Ball(6));
        List<Ball> balls2 = Arrays.asList(
                new Ball(1), new Ball(2), new Ball(3), new Ball(4), new Ball(5), new Ball(7));
        List<Ball> balls3 = Arrays.asList(
                new Ball(1), new Ball(2), new Ball(3), new Ball(4), new Ball(5), new Ball(45));
        List<Ball> balls4 = Arrays.asList(
                new Ball(1), new Ball(2), new Ball(3), new Ball(4), new Ball(44), new Ball(45));
        List<Ball> balls5 = Arrays.asList(
                new Ball(1), new Ball(2), new Ball(3), new Ball(43), new Ball(44), new Ball(45));

        List<Lotto> lottoBundle = Arrays.asList(new Lotto(balls1), new Lotto(balls2), new Lotto(balls3), new Lotto(balls4), new Lotto(balls5));
        lottos = new Lottos(lottoBundle);

        winningBalls = new WinningBalls("1,2,3,4,5,6");
        bonusBall = new BonusBall("7", winningBalls);

        expectedMatchResults = new HashMap<>();
        expectedMatchResults.put(MatchResult.THREE_MATCH, 1);
        expectedMatchResults.put(MatchResult.FOUR_MATCH, 1);
        expectedMatchResults.put(MatchResult.FIVE_MATCH, 1);
        expectedMatchResults.put(MatchResult.FIVE_MATCH_WITH_BONUS_BALL, 1);
        expectedMatchResults.put(MatchResult.SIX_MATCH, 1);
        expectedMatchResults.put(MatchResult.NONE, 0);
    }

    @DisplayName("로또 한 줄과 당첨번호를 비교했을때 올바른 당첨 결과를 반환하는지 확인")
    @ParameterizedTest
    @MethodSource("lottosProvider")
    void findMatchResultTest(Lotto lotto, MatchResult expectedMatchResult) {
        assertThat(MatchResult.findMatchResult(lotto, winningBalls, bonusBall)).isEqualTo(expectedMatchResult);
    }

    @DisplayName("생성된 로또들과 당첨번호를 비교했을 때 올바른 당첨 결과를 반환하는지 확인")
    @Test
    void createMatchResultsTest() {
        Map<MatchResult, Integer> matchResults = Results.createMatchResults(lottos, winningBalls, bonusBall);
        assertThat(matchResults).isEqualTo(expectedMatchResults);
    }

    @DisplayName("올바른 수익률을 반환하는지 확인")
    @Test
    void calculateEarningRateTest() {
        Results results = new Results(lottos, winningBalls, bonusBall);
        int earningRate = results.calculateEarningRate(new PurchasePrice("5000"));
        assertThat(earningRate).isEqualTo(40_631_100);
    }
}
