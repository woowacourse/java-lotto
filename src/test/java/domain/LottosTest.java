package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.dto.ResultResponse;
import java.util.EnumMap;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 정상_결과_반환_테스트() {
        //given
        Lottos lottos = createFakeLottos();
        WinningLotto winningLotto = new WinningLotto("5, 12, 19, 23, 34, 42", "27");
        Amount amount = new Amount("8000");

        EnumMap<Rank, Integer> expectedRankCount = createExpectedRankCount();

        int expectedSumProfit = 2031605000;
        double expectedProfit = Math.floor((double) expectedSumProfit / 8000 * 100) / 100;

        ResultResponse resultResponse = new ResultResponse(expectedRankCount, expectedProfit);
        //when
        ResultResponse result = lottos.getResult(winningLotto, amount);

        //then
        assertThat(resultResponse).isEqualTo(result);
    }

    private static EnumMap<Rank, Integer> createExpectedRankCount() {
        EnumMap<Rank, Integer> expectedRankCount = new EnumMap<>(Rank.class);
        expectedRankCount.put(Rank.FIRST, 1);
        expectedRankCount.put(Rank.SECOND, 1);
        expectedRankCount.put(Rank.THIRD, 1);
        expectedRankCount.put(Rank.FOURTH, 2);
        expectedRankCount.put(Rank.FIFTH, 1);
        expectedRankCount.put(Rank.NONE, 2);

        return expectedRankCount;
    }

    private Lottos createFakeLottos() {
        Lottos lottos = new Lottos();

        lottos.add(new Lotto("5, 12, 19, 23, 34, 42")); //1등
        lottos.add(new Lotto("5, 12, 19, 23, 27, 34")); //2등
        lottos.add(new Lotto("5, 12, 19, 23, 34, 40")); //3등
        lottos.add(new Lotto("5, 12, 19, 20, 24, 34")); //4등
        lottos.add(new Lotto("5, 12, 19, 23, 27, 33")); //보너스 맞춰도 4등
        lottos.add(new Lotto("5, 12, 19, 24, 30, 38")); //5등
        lottos.add(new Lotto("5, 10, 20, 24, 30, 38")); //당첨x
        lottos.add(new Lotto("1, 10, 20, 24, 30, 38")); //당첨x

        return lottos;
    }
}