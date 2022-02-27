package lotto.model.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.prize.Prize;
import lotto.model.prize.PrizeInformations;

public class PrizeInformationDTO {
    private final int matchingCount;
    private final Boolean bonus;
    private final int amount;
    private final int prizeCount;

    private PrizeInformationDTO(int matchingCount, Boolean bonus, int amount, int prizeCount) {
        this.matchingCount = matchingCount;
        this.bonus = bonus;
        this.amount = amount;
        this.prizeCount = prizeCount;
    }

    public static List<PrizeInformationDTO> from(PrizeInformations prizeInformations) {
        return prizeInformations.getPrizeInformations().stream()
                .map(prizeInformation -> {
                    Prize prize = prizeInformation.getPrize();
                    return new PrizeInformationDTO(
                            prize.getMatchCount(), prize.isBonus(), prize.getAmount(), prizeInformation.getCount());

                })
                .collect(Collectors.toList());
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrizeCount() {
        return prizeCount;
    }
}
