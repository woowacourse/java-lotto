package lotto.view;

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
            System.out.println("거스름 돈은 " + change + "원 입니다.");
        }
    }

    public static void printLottoTicketGroup(LottoTicketGroup manualLottos, LottoTicketGroup autoLottos) {
        System.out.println("\n수동으로 " + manualLottos.size() + "장, 자동으로 " + autoLottos.size() + "개를 구매했습니다.");
        drawLottoTickets(manualLottos);
        drawLottoTickets(autoLottos);
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

        System.out.println("총 수익률은 " + lottoResult.earningRate() + "%입니다.");
    }

    private static void drawRank(LottoRank lottoRank, int countOfRank) {
        if (lottoRank.equals(LottoRank.FAIL)) {
            return;
        }
        drawRankInfo(lottoRank);
        System.out.println("- " + countOfRank + "개");
    }

    private static void drawRankInfo(LottoRank lottoRank) {
        System.out.print(lottoRank.getCountOfMatch() + "개 일치");
        if (lottoRank.equals(LottoRank.SECOND)) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.print(" (" + lottoRank.getReward() + "원)");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message + "\n");
    }
}