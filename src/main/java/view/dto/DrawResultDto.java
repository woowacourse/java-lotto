package view.dto;

import domain.result.LottoRank;

import java.util.Map;

public class DrawResultDto {
    private final Map<LottoRank, Integer> matches;

    public DrawResultDto(Map<LottoRank, Integer> matches) {
        this.matches = matches;
    }

    public Map<LottoRank, Integer> getMatches() {
        return matches;
    }
}
