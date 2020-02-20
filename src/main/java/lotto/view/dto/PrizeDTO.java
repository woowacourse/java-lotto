package lotto.view.dto;

import lotto.domain.PrizeGroup;

import java.util.List;

import static lotto.domain.PrizeGroup.SECOND;

public class PrizeDTO {
    private static final String MESSAGE_FOR_BONUS_CASE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String MESSAGE_FOR_DEFAULT_CASE = "%d개 일치 (%d원)- %d개";
    private final String name;
    private final int matchCount;
    private final int defaultPrize;
    private final int matchTicketCount;
    private final String message;

    public PrizeDTO(PrizeGroup criteria, List<PrizeGroup> prizeResults) {
        this.name = criteria.name();
        this.matchCount = criteria.getMatchCount();
        this.defaultPrize = criteria.getDefaultPrize();
        this.matchTicketCount = (int) prizeResults.stream()
                .filter(criteria::equals)
                .count();
        this.message = createMessage(criteria);
    }

    private String createMessage(PrizeGroup prizeGroup) {
        if (SECOND.equals(prizeGroup)) {
            return MESSAGE_FOR_BONUS_CASE;
        }
        return MESSAGE_FOR_DEFAULT_CASE;
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

    public String getMessage() {
        return message;
    }
}
