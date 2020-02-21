package lotto.domain.result;

import lotto.domain.result.win.rank.Rank;

import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class LottoResultBundle {
    private static final String RATE_PERCENT = "%";
    private static final String CUT_DIGIT_FORMAT = "0";
    private static final int PERCENTAGE = 100;

    private final Map<Rank, Integer> results;

    public LottoResultBundle(List<Rank> ranks) {
        this.results = new EnumMap<>(Rank.class);
        makeRanks(ranks);
    }

    private void makeRanks(List<Rank> ranks) {
        for (Rank rank : Rank.values()) {
            addResult(rank, ranks);
        }
    }

    private void addResult(Rank rank, List<Rank> ranks) {
        int matchTicketCount = (int) ranks.stream()
                .filter(aRank -> aRank.equals(rank))
                .count();
        this.results.put(rank, matchTicketCount);
    }

    public String getRate() {
        double bettingMoney = getBettingMoney();
        double totalPrize = getTotalPrize();

        DecimalFormat decimalFormat = new DecimalFormat(CUT_DIGIT_FORMAT);
        String rate = decimalFormat.format(totalPrize / bettingMoney * PERCENTAGE);
        return rate + RATE_PERCENT;
    }

    private int getBettingMoney() {
        return this.results.values().stream()
                .reduce(0, Integer::sum) * LOTTO_PRICE;
    }

    private double getTotalPrize() {
        return results.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getDefaultPrize() * entry.getValue())
                .reduce(0, Double::sum);
    }

    public int getMatchCount(Rank rank) {
        return this.results.get(rank);
    }

    public int getMatchTicketCount(Rank rank) {
        return this.results.get(rank);
    }

}
