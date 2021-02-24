package view.dto;

import domain.result.LottoRank;

import java.util.Map;

public class LottoGameResultDto {
    private final Map<LottoRank, Integer> matches;

    public LottoGameResultDto(final Map<LottoRank, Integer> matches) {
        this.matches = matches;
    }

    public Map<LottoRank, Integer> getMatches() {
        return matches;
    }
}
