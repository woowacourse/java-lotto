package lotto.view.dto;

import lotto.domain.result.win.prize.PrizeGroup;

import java.util.List;

public class PrizeResponseDTO {
    private final PrizeGroup prizeGroup;
    private final int matchTicketCount;

    public PrizeResponseDTO(PrizeGroup prizeGroup, List<PrizeGroup> prizeResults) {
        this.prizeGroup = prizeGroup;
        this.matchTicketCount = (int) prizeResults.stream()
                .filter(prizeGroup::equals)
                .count();
    }

    public double getSum() {
        return prizeGroup.getDefaultPrize() * matchTicketCount;
    }

    public String getName() {
        return prizeGroup.name();
    }

    public int getMatchCount() {
        return prizeGroup.getMatchCount();
    }

    public int getDefaultPrize() {
        return prizeGroup.getDefaultPrize();
    }

    public int getMatchTicketCount() {
        return matchTicketCount;
    }
}
