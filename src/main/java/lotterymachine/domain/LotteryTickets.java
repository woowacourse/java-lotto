package lotterymachine.domain;

import lotterymachine.view.InputView;

import java.util.*;

public class LotteryTickets {
    private final List<LotteryTicket> tickets;

    public LotteryTickets(List<LotteryTicket> tickets) {
        this.tickets = tickets;
    }

    public static LotteryTickets of(List<LotteryTicket> autoTicket, List<LotteryTicket> passivityTicket) {
        List<LotteryTicket> tickets = new ArrayList<>();
        tickets.addAll(autoTicket);
        tickets.addAll(passivityTicket);
        return new LotteryTickets(tickets);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(tickets);
    }
}