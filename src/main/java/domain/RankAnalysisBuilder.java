package domain;

public class RankAnalysisBuilder {
    private final WinningLotto winningLotto;
    private final Counter rankCounter;

    public RankAnalysisBuilder(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
        this.rankCounter = Counter.create();
    }

    public void add(Lotto lotto) {
        Rank rank = winningLotto.match(lotto);

        rankCounter.put(rank);
    }

    public RankAnalysis toRankAnalysis() {
        return RankAnalysis.from(rankCounter);
    }
}
