package lotto.view;

import lotto.domain.LottoQuantity;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.purchaseamount.PurchaseAmount;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputView {
    public static void printChange(PurchaseAmount purchaseAmount) {
        int change = purchaseAmount.available();
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

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계\n----------");

        List<LottoRank> lottoRanks = Arrays.asList(LottoRank.values());
        Collections.reverse(lottoRanks);
        lottoRanks.forEach(x -> drawRank(x, lottoResult.countOfRank(x)));

        System.out.format("총 수익률은 %.6f%%입니다.%n", lottoResult.earningRate());
    }

    private static void drawRank(LottoRank lottoRank, int countOfRank) {
        if (lottoRank.equals(LottoRank.FAIL)) {
            return;
        }
        drawRankInfo(lottoRank);
        System.out.format("- %d개\n", countOfRank);
    }

    private static void drawRankInfo(LottoRank lottoRank) {
        System.out.format("%d개 일치", lottoRank.getCountOfMatch());
        if (lottoRank.isBonusMatch()) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.format(" (%d원)", lottoRank.getReward());
    }

    public static void printErrorMessage(String message) {
        System.out.println(message + "\n");
    }
}