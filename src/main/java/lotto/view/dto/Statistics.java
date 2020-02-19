package lotto.view.dto;

import lotto.domain.PrizeGroup;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoTicket.LOTTO_PRICE;

public class Statistics {
    private final Map<PrizeGroup, StatisticsDetail> statistics;

    public Statistics(List<PrizeGroup> prizeGroups) {
        this.statistics = new EnumMap<>(PrizeGroup.class);
        for (PrizeGroup aPrize : PrizeGroup.values()) {
            StatisticsDetail statisticsDetail = new StatisticsDetail(aPrize, prizeGroups);
            this.statistics.put(aPrize, statisticsDetail);
        }
    }

    public double getRate() {
        double bettingMoney = getBettingMoney();
        double totalPrize = getPrize();
        return totalPrize / bettingMoney;
    }

    private int getBettingMoney() {
        return statistics.values().stream()
                .mapToInt(StatisticsDetail::getCount)
                .sum() * LOTTO_PRICE;
    }

    private double getPrize() {
        return this.statistics.values().stream()
                .mapToDouble(StatisticsDetail::getSum)
                .sum();
    }
}
