package lotto.view.dto;

import lotto.domain.PrizeGroup;

import java.util.List;

public class StatisticsDetail {
    private final int defaultPrize;
    private final int count;

    public StatisticsDetail(PrizeGroup criteria, List<PrizeGroup> prizeGroups) {
        this.defaultPrize = criteria.getMoney();
        this.count = (int) prizeGroups.stream()
                .filter(criteria::equals)
                .count();
    }

    public int getDefaultPrize() {
        return defaultPrize;
    }

    public int getCount() {
        return count;
    }

    public double getSum() {
        return defaultPrize * count;
    }
}
