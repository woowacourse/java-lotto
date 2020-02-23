package lotto.view.dto;

import lotto.domain.result.rank.Rank;

import java.util.List;

public class RankDTO {
    private final String name;
    private final int matchCount;
    private final int defaultPrize;
    private final int matchTicketCount;

    public RankDTO(Rank rank, List<Rank> lottoRankResult) {
        this.name = rank.name();
        this.matchCount = rank.getMatchCount();
        this.defaultPrize = rank.getDefaultPrize();
        this.matchTicketCount = (int) lottoRankResult.stream()
                .filter(rank::equals)
                .count();
    }

    public double getSum() {
        return defaultPrize * matchTicketCount;
    }

    public String getName() {
        return name;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getDefaultPrize() {
        return defaultPrize;
    }

    public int getMatchTicketCount() {
        return matchTicketCount;
    }
}
