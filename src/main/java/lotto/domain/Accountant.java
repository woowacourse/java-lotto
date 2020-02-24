package lotto.domain;

public class Accountant {

    public static String calculate(Money money) {
        double total = 0;
        for (Rank rank : Rank.result.keySet()) {
            total += rank.getPrize() * Rank.result.get(rank);
        }
        double rate = total * 100 / (money.calculateTicketQuantity() * Money.TICKET_PRICE);
        return String.format("%.2f", rate);
    }
}
