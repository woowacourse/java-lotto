package lotterymachine.model;

import java.util.List;
import java.util.stream.Collectors;
import lotterymachine.dto.ManualTicketDto;
import lotterymachine.utils.LotteryGenerator;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;

public class TicketMachine {
    public LotteryTickets purchaseManualTickets(List<ManualTicketDto> manualTickets) {
        List<LotteryTicket> lotteryTickets = manualTickets.stream()
                .map(ManualTicketDto::getTickets)
                .map(Ball::getBalls)
                .map(LotteryTicket::new)
                .collect(Collectors.toUnmodifiableList());
        return new LotteryTickets(lotteryTickets);
    }

    public LotteryTickets purchaseAutoTickets(Count numberOfTickets) {
        return new LotteryTickets(LotteryGenerator.generate(numberOfTickets));
    }
}
