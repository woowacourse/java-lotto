package dto;

import domain.LottoRank;
import java.util.List;
import java.util.Map;

public record WinningRecipeGroup(
        List<WinningRecipe> winningRecipes
) {
    public static WinningRecipeGroup of(final Map<LottoRank, Integer> winningResult) {
        return new WinningRecipeGroup(winningResult.entrySet().stream()
                .map(WinningRecipe::of)
                .toList());
    }
}
