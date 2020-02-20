package lotto.view.dto;

import lotto.domain.PrizeGroup;

import java.util.List;

public class PrizeDTO {
    private final String name;
    private final int matchCount;
    private final int defaultPrize;
    private final int matchTicketCount;

    public PrizeDTO(PrizeGroup criteria, List<PrizeGroup> prizeResults) {
        this.name = criteria.name();
        this.matchCount = criteria.getMatchCount();
        this.defaultPrize = criteria.getDefaultPrize();
        this.matchTicketCount = (int) prizeResults.stream()
                .filter(criteria::equals)
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
