package controller.dto;

import model.LottoRank;

public record LottoRankResponse(
        int overlappedCount,
        int prizeMoney,
        boolean isBonusMatched,
        int rankMatchCount
) {

    public static LottoRankResponse of(LottoRank lottoRank, int rankMatchCount) {
        return new LottoRankResponse(
                lottoRank.getOverlappedCount(),
                lottoRank.getPrizeMoney(),
                lottoRank.isRequiredBonusNumber(),
                rankMatchCount
        );
    }
}
