package lotterymachine.domain;

import java.util.ArrayList;
import java.util.List;

public class LotteryTicketsController {
    public LotteryTickets createLotteryTickets(LotteryPurchaseCount lotteryPurchaseCount
            , List<List<Integer>> manualLotteryNumbers) {
        List<LotteryTicket> manualLotteryTickets = createManualLotteryTickets(manualLotteryNumbers);
        List<LotteryTicket> autoLotteryTickets = createAutoLotteryTickets(lotteryPurchaseCount);
        return createLotteryTickets(manualLotteryTickets, autoLotteryTickets, lotteryPurchaseCount);
    }

    private LotteryTickets createLotteryTickets(List<LotteryTicket> manualLotteryTickets
            , List<LotteryTicket> autoLotteryTickets
            , LotteryPurchaseCount lotteryPurchaseCount) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        lotteryTickets.addAll(manualLotteryTickets);
        lotteryTickets.addAll(autoLotteryTickets);
        return new LotteryTickets(lotteryTickets, lotteryPurchaseCount);
    }

    private List<LotteryTicket> createManualLotteryTickets(List<List<Integer>> manualLotteryNumbers) {
        return LotteryTicket.createLotteryTickets(manualLotteryNumbers);
    }

    private List<LotteryTicket> createAutoLotteryTickets(LotteryPurchaseCount lotteryPurchaseCount) {
        AutoLotteryTicketGenerator autoLotteryTicketGenerator = new AutoLotteryTicketGenerator(
                lotteryPurchaseCount.getAutoValue()
                , new RandomLotteryNumbersGenerator());
        return autoLotteryTicketGenerator.createLotteryTickets();
    }
}
