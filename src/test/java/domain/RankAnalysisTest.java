package domain;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankAnalysisTest {
    private static final List<Rank> initiatedRanks = Arrays.asList(
            Rank.FIRST,
            Rank.FIFTH,
            Rank.MISS,
            Rank.MISS
    );
    private static final Counter initiatedCounter = Counter.create();

    static {
        for (Rank rank : initiatedRanks) {
            initiatedCounter.put(rank);
        }
    }

    @Test
    void count_초기화된Counter_FIRST확인() {
        RankAnalysis analysis = RankAnalysis.from(initiatedCounter);

        assertThat(analysis.count(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void count_초기화된Counter_MISS확인() {
        RankAnalysis analysis = RankAnalysis.from(initiatedCounter);

        assertThat(analysis.count(Rank.MISS)).isEqualTo(2);
    }

    @Test
    void getEarningRate_userLotto가_안추가되었을때() {
        RankAnalysis analysis = RankAnalysis.from(Counter.create());

        assertThat(analysis.getEarningRate()).isEqualTo(0, Offset.offset(0.0099));
    }

    @Test
    void getEarningRate() {
        RankAnalysis analysis = RankAnalysis.from(initiatedCounter);
        // Todo: 테스트가 너무 복잡한건 아닌가?
        long totalWinningMoney = initiatedRanks.stream().mapToLong(Rank::getWinningMoney).sum();
        double expected = totalWinningMoney / (double) (Lotto.PRICE.toInt() * initiatedRanks.size());

        assertThat(analysis.getEarningRate()).isEqualTo(expected, Offset.offset(0.0099));
    }
}