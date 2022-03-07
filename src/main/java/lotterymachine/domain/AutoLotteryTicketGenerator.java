package lotterymachine.domain;

import java.util.ArrayList;
import java.util.List;

public class AutoLotteryTicketGenerator {
    private final int autoCount;
    private final LotteryNumbersGenerator randomLotteryNumbersGenerator;

    public AutoLotteryTicketGenerator(int autoCount, LotteryNumbersGenerator randomLotteryNumbersGenerator) {
        this.autoCount = autoCount;
        this.randomLotteryNumbersGenerator = randomLotteryNumbersGenerator;
    }

    public List<LotteryTicket> createLotteryTickets() {
        final List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < this.autoCount; i++) {
            LotteryTicket lotteryTicket = new LotteryTicket(randomLotteryNumbersGenerator.generate());
            lotteryTickets.add(lotteryTicket);
        }
        return lotteryTickets;
    }
}
