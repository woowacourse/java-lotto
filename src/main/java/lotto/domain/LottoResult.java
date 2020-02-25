package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {

    private Map<Rank, Integer> result = new HashMap<>();

    void updateResult(Rank rank) {
        result.put(rank, result.getOrDefault(rank, 0) + 1);
    }

    public String calculateRate(Money money) {
        double total = 0;
        for (Rank rank : result.keySet()) {
            total += rank.getPrize() * result.get(rank);
        }
        double rate = total * 100 / (money.calculateTicketQuantity() * Money.TICKET_PRICE);
        return String.format("%.2f", rate);
    }

    int countSpecificRank(Rank rank) {
        return result.get(rank);
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

}
