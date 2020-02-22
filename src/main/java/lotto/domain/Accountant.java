package lotto.domain;

import java.util.Map;

public class Accountant {

    public static String calculate(Money money, LottoResult result) {
        double total = 0;
        Map<Rank, Integer> lottoResult = result.getLottoResult();
        for (Rank rank : lottoResult.keySet()) {
            total += (double)rank.getPrize() * lottoResult.get(rank);
        }
        double rate = total * 100 / (money.ticketQuantity() * Money.TICKET_PRICE);
        return String.format("%.2f", rate);
    }
}
