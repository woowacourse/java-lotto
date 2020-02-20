package lotto.domain;

import java.util.HashMap;

public class Accountant {

    public static String calculate(Money money) {
        double total = 0;
        for (Rank rank : Rank.result.keySet()) {
            total += rank.getPrize() * Rank.result.get(rank);
        }
        double rate = total * 100 / (money.ticketQuantity() * Money.TICKET_PRICE);
        return String.format("%.2f", rate);
    }

    static String calculate(Money money, HashMap<Rank, Integer> result) {
        double total = 0;
        for (Rank rank : result.keySet()) {
            total += rank.getPrize() * result.get(rank);
        }
        double rate = total * 100 / (money.ticketQuantity() * Money.TICKET_PRICE);
        return String.format("%.2f", rate);
    }
}
