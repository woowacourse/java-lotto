package lotterymachine;

import lotterymachine.domain.LotteryTicket;
import lotterymachine.domain.LotteryTickets;
import lotterymachine.utils.LotteryNumbersGenerator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Converter {
    public static LotteryPurchase createLotteryPurchase() {
        int amount = InputView.getAmount();
        System.out.println();
        int passivityPurchaseCount = InputView.getPassivityPurchaseCount();
        return new LotteryPurchase(amount, passivityPurchaseCount);
    }

    public static LotteryTickets createLotteryTickets(LotteryPurchase lotteryPurchase) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        lotteryTickets.addAll(createPassivityLotteryTickets(lotteryPurchase.getPassivityCount()));
        lotteryTickets.addAll(createAutoLotteryTickets(lotteryPurchase.getAutoCount()));
        return new LotteryTickets(lotteryTickets);
    }

    private static List<LotteryTicket> createAutoLotteryTickets(int count) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            LotteryTicket lotteryTicket = new LotteryTicket(LotteryNumbersGenerator.generate());
            lotteryTickets.add(lotteryTicket);
        }
        return lotteryTickets;
    }

    private static List<LotteryTicket> createPassivityLotteryTickets(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lotteryTickets.add(InputView.getPassivityLotteryTicket());
        }
        return lotteryTickets;
    }
}
