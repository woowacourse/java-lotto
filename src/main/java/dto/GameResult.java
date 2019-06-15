package dto;

import domain.LottoGroup;
import domain.WinningLotto;

public class GameResult {
    private final int round;
    private final LottoGroup lottoGroup;
    private final WinningLotto winningLotto;
    private final RankAnalysisDTO rankAnalysisDTO;

    private GameResult(int round, LottoGroup lottoGroup, WinningLotto winningLotto, RankAnalysisDTO rankAnalysisDTO) {
        this.round = round;
        this.lottoGroup = lottoGroup;
        this.winningLotto = winningLotto;
        this.rankAnalysisDTO = rankAnalysisDTO;
    }

    public static GameResult of(int round, LottoGroup lottoGroup, WinningLotto winningLotto, RankAnalysisDTO rankAnalysisDTO) {
        return new GameResult(round, lottoGroup, winningLotto, rankAnalysisDTO);
    }

    public int getRound() {
        return round;
    }

    public LottoGroup getLottoGroup() {
        return lottoGroup;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public RankAnalysisDTO getRankAnalysisDTO() {
        return rankAnalysisDTO;
    }
}
