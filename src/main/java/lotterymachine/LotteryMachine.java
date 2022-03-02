package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.view.OutputView;

public class LotteryMachine {
    public static void main(String[] args) {
        LotteryPurchase lotteryPurchase = LotteryConverter.createLotteryPurchase();
        LotteryTickets lotteryTickets = LotteryConverter.createLotteryTickets(lotteryPurchase);
        OutputView.printLotteryTickets(lotteryPurchase, lotteryTickets);

        WinningLottery winningLottery =  LotteryConverter.createWinningLottery();
//        Map<WinningLotteryRank, Integer> lotteryTicketResult = lotteryTickets.getLotteriesResult(winningLottery);
//        OutputView.printWinningLotteryResults(lotteryTicketResult);
//        OutputView.printProfitRate(getTotalProfitRate(lotteryPurchase, lotteryTicketResult));
    }

//    private static LotteryTickets createLotteryTickets(int purchaseCount) {
//        List<LotteryTicket> lotteryTickets = new ArrayList<>();
//        for (int i = 0; i < purchaseCount; i++) {
//            lotteryTickets.add(new LotteryTicket(LotteryNumbersGenerator.generate()));
//        }
//        return new LotteryTickets(lotteryTickets);
//    }
//
//    private static double getTotalProfitRate(LotteryPurchase lotteryPurchase, Map<WinningLotteryRank, Integer> lotteryTicketResult) {
//        int totalProfit = LotteryCalculator.totalProfit(lotteryTicketResult);
//        return calculateProfitRate(totalProfit, lotteryPurchase.getPurchasePrice());
//    }
}
