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
        // Todo: 테스트가 너무 복잡한건 아닌가? (getEarningRate_상금이_매우_클때() 와 같이... 내가 계산한 값을 넣는게 맞으려나??)
        double expected = getTotalWinningMoney(initiatedRanks) / (double) getTotalUsedMoney(initiatedRanks);

        assertThat(analysis.getEarningRate()).isEqualTo(expected, Offset.offset(0.0099));
    }

    @Test
    void getEarningRate_상금이_매우_클때() {
        List<Rank> ranks = Arrays.asList(
                Rank.FIRST,
                Rank.FIRST,
                Rank.FIRST,
                Rank.FIRST,
                Rank.SECOND,
                Rank.SECOND,
                Rank.SECOND,
                Rank.SECOND,
                Rank.SECOND,
                Rank.SECOND
        );
        RankAnalysis analysis = RankAnalysis.from(createCounter(ranks));
        // expected == 818000
        double expected = getTotalWinningMoney(ranks) / (double) getTotalUsedMoney(ranks);

        assertThat(analysis.getEarningRate()).isEqualTo(818000, Offset.offset(0.0099));
    }

    private long getTotalWinningMoney(List<Rank> ranks) {
        return ranks.stream().mapToLong(Rank::getWinningMoney).sum();
    }

    private long getTotalUsedMoney(List<Rank> ranks) {
        return Lotto.PRICE.toInt() * (long) ranks.size();
    }

    private static Counter<Rank> createCounter(List<Rank> ranks) {
        Counter<Rank> counter = Counter.create();

        for (Rank rank : ranks) {
            counter.put(rank);
        }

        return counter;
    }
}