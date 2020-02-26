package lotto.view;

import lotto.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class OutputView {

    public static void printErrorMessage(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void printStartGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }


    public static void printChangeMoney(int changeMoney) {
        System.out.printf("거스름돈은 %d원 입니다.\n", changeMoney);
    }

    public static void printManualLottoTicketingCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printManualLottoTicketing() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printLottoTicket(TicketingCount ticketingCount, TicketingCount manualTicketingCount) {
        List<LottoTicket> lottoTickets = LottoTickets.getLottoTickets();

        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n"
                , manualTicketingCount.getTicketingCount(), ticketingCount.getTicketingCount());

        lottoTickets.forEach(lottoTicket ->
                System.out.println(lottoTicket.getLottoTicket()
                        .stream()
                        .map(LottoBall::getLottoNumber)
                        .collect(Collectors.toList())));
    }

    public static void printAnswerWinningBalls() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printAnswerBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }


    public static void printResultAllOfRank(List<WinningRank> winningRanks, EarningRate earningRate) {
        List<WinningRank> winningRankSet = new ArrayList<>(Arrays.asList(WinningRank.values()));
        winningRankSet.remove(WinningRank.NO_RANK);

        OutputView.printRankConstant();
        winningRankSet.forEach(winningRank ->
                OutputView.printEachWinningResult(winningRank, earningRate.countRankPeople(winningRanks, winningRank)));
    }

    public static void printRankConstant() {
        System.out.println("당첨통계");
        System.out.println("---------");
    }

    public static void printEachWinningResult(WinningRank winningRank, long count) {
        System.out.printf("%d개 일치", winningRank.getWinningBallCount());
        if (winningRank.getWinningMoney() == WinningRank.SECOND_RANK.getWinningMoney()) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.printf(" (%d원) - %d개\n",
                winningRank.getWinningMoney(), count);
    }

    public static void printEarningRate(EarningRate earningRate) {
        System.out.printf("총 수익률은 %.1f%% 입니다.", earningRate.getEarningRate());
    }
}
