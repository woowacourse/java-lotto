package dto;

import domain.LottoRank;
import java.util.Map.Entry;

public record WinningRecipe(
        int rank,
        int matchCount,
        int price,
        int matchQuantity
) {
    public static WinningRecipe of(final Entry<LottoRank, Integer> entry) {
        final LottoRank lottoRank = entry.getKey();
        final Integer matchQuantity = entry.getValue();
        return new WinningRecipe(
                lottoRank.getRank(),
                lottoRank.getMatchedCount(),
                lottoRank.getPrice(),
                matchQuantity
        );
    }
}
