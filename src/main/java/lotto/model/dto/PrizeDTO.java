package lotto.model.dto;

import lotto.model.prize.Prize;

public class PrizeDTO {
    private final int matchingCount;
    private final boolean bonus;
    private final long amount;

    private PrizeDTO(int matchingCount, boolean bonus, long amount) {
        this.matchingCount = matchingCount;
        this.bonus = bonus;
        this.amount = amount;
    }

    public static PrizeDTO from(Prize prize) {
        return new PrizeDTO(prize.getMatchCount(), prize.isBonus(), prize.getAmount());
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public boolean isBonus() {
        return bonus;
    }

    public long getAmount() {
        return amount;
    }
}
