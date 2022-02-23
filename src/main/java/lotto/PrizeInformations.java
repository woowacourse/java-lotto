package lotto;

import java.util.List;

public class PrizeInformations {
    private final List<PrizeInformation> prizeInformations;

    public PrizeInformations(List<PrizeInformation> prizeInformations) {
        this.prizeInformations = prizeInformations;
    }

    public double calculateEarningRate(Money money) {
        return money.rate(getTotalAmount());
    }

    private int getTotalAmount() {
        return prizeInformations.stream()
                .mapToInt(PrizeInformation::pickAmount)
                .sum();
    }
}
