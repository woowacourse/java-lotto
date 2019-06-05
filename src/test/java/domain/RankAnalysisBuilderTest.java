package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankAnalysisBuilderTest {
    private final Lotto lotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final WinningLotto winningLotto = WinningLotto.of(lotto, Number.from(45));

    @Test
    void 서로다른_두개추가했을때_올바르게_생성하는지() {
        List<Lotto> userLottos = Arrays.asList(
                LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)), // first
                LottoGenerator.from(Arrays.asList(1, 2, 3, 34, 35, 36)) // fifth
        );
        Counter counter = Counter.create();
        RankAnalysisBuilder builder = new RankAnalysisBuilder(winningLotto);
        for (Lotto userLotto : userLottos) {
            builder.add(userLotto);
            counter.put(winningLotto.match(userLotto));
        }

        assertThat(builder.toRankAnalysis()).isEqualTo(RankAnalysis.from(counter));
    }

    @Test
    void 동일한_두개추가했을때_올바르게_생성하는지() {
        Lotto first = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)); // first
        List<Lotto> userLottos = Arrays.asList(first, first);
        Counter counter = Counter.create();
        RankAnalysisBuilder builder = new RankAnalysisBuilder(winningLotto);
        for (Lotto userLotto : userLottos) {
            builder.add(userLotto);
            counter.put(winningLotto.match(userLotto));
        }

        assertThat(builder.toRankAnalysis()).isEqualTo(RankAnalysis.from(counter));
    }
}