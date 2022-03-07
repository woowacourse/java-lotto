package lotterymachine.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotterymachine.utils.LotteryGenerator;
import lotterymachine.vo.Count;

public class TicketMachine {
    public LotteryTickets purchase(Count autoTickets, List<LotteryTicket> manualLotteryTickets) {
        List<LotteryTicket> autoLotteryTickets = LotteryGenerator.generate(autoTickets);
        return collectTickets(manualLotteryTickets, autoLotteryTickets);
    }

    private static LotteryTickets collectTickets(List<LotteryTicket> manualTickets, List<LotteryTicket> autoTickets) {
        return new LotteryTickets(Stream.concat(manualTickets.stream(), autoTickets.stream())
                .collect(Collectors.toUnmodifiableList()));
    }
}
