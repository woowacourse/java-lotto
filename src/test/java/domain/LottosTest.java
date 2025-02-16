package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_등수_매치_정상_진행_테스트() {
        Lottos lottos = createLottos();
        WinningLotto winningLotto = new WinningLotto(List.of(5, 12, 19, 23, 34, 42), "27");

        EnumMap<Rank, Integer> expectedRankCount = createExpectedRankCount();

        assertThat(expectedRankCount).isEqualTo(lottos.countMatchNumbers(winningLotto));
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

    private Lottos createLottos() {
        Lottos lottos = new Lottos(List.of(new Lotto(List.of(5, 12, 19, 23, 34, 42)), //1등
                new Lotto(List.of(5, 12, 19, 23, 27, 34)), //2등
                new Lotto(List.of(5, 12, 19, 23, 34, 40)), //3등
                new Lotto(List.of(5, 12, 19, 20, 24, 34)), //4등
                new Lotto(List.of(5, 12, 19, 23, 27, 33)), //4등
                new Lotto(List.of(5, 12, 19, 24, 30, 38)), //5등
                new Lotto(List.of(5, 10, 20, 24, 30, 38)), //당첨x
                new Lotto(List.of(1, 10, 20, 24, 30, 38)))); //당첨x

        return lottos;
    }
}