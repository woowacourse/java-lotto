package domain;

public class LottoSimulationResult {
    public final RankAnalysis rankAnalysis;
    public final LottoGroup lottoGroup;

    private LottoSimulationResult(RankAnalysis rankAnalysis, LottoGroup lottoGroup) {
        this.rankAnalysis = rankAnalysis;
        this.lottoGroup = lottoGroup;
    }

    public static LottoSimulationResult of(RankAnalysis rankAnalysis, LottoGroup lottoGroup) {
        return new LottoSimulationResult(rankAnalysis, lottoGroup);
    }
}
