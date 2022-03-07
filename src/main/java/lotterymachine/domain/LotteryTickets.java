package lotterymachine.domain;

import java.util.Collections;
import java.util.List;

public class LotteryTickets {
    private static final String NOT_CORRECT_SIZE = "로또 구매 개수와 생성하려는 로또 개수가 일치하지 않습니다.";

    private final List<LotteryTicket> tickets;

    public LotteryTickets(List<LotteryTicket> tickets, LotteryPurchaseCount lotteryPurchaseCount) {
        validateCorrectSize(tickets, lotteryPurchaseCount);
        this.tickets = tickets;
    }

    private void validateCorrectSize(List<LotteryTicket> tickets, LotteryPurchaseCount lotteryPurchaseCount) {
        if (tickets.size() != lotteryPurchaseCount.getTotalValue()) {
            throw new IllegalArgumentException(NOT_CORRECT_SIZE);
        }
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(tickets);
    }
}