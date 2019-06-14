package lotto.view;

import lotto.domain.LottoQuantity;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.RankCount;
import lotto.domain.purchaseamount.PurchaseAmount;

public class OutputView {
    public static void printChange(PurchaseAmount purchaseAmount) {
        int change = purchaseAmount.getMoney();
        if (change != 0) {
            System.out.format("거스름 돈은 %d원 입니다.\n\n", change);
        }
    }

    public static void printLottoTickets(LottoTicketGroup lottos, LottoQuantity manualLottoQuantity, LottoQuantity autoLottoQuantity) {
        System.out.format("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottoQuantity.getQuantity(), autoLottoQuantity.getQuantity());
        drawLottoTickets(lottos);
        System.out.println();
    }

    private static void drawLottoTickets(LottoTicketGroup lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public static void printLottoResult(List<RankCount> rankCounts, BigDecimal earningRate) {
        System.out.println("\n당첨 통계\n----------");
        rankCounts.stream()
                .filter(RankCount::isWinningResult)
                .forEach(OutputView::drawStatistic)
        ;
        System.out.println("\n총 수익률은 " + earningRate + "%입니다.");
    }

    private static void drawStatistic(RankCount rankCount) {
        LottoRank rank = rankCount.getRank();
        int count = rankCount.getCount();

        System.out.format("%d개 일치%s (%d원)- %d개\n",
                rank.getCountOfMatch(),
                rank.isBonusMatch() ? ", 보너스 볼 일치" : "",
                rank.getReward(),
                count
        );

    }

    public static void printErrorMessage(String message) {
        System.out.println(message + "\n");
    }
}