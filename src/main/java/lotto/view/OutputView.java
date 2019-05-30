package lotto.view;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lotto.LottoTicketGroup;

public class OutputView {
    public static void printLottoTicketGroup(LottoTicketGroup lottoTickets) {
        System.out.println("\n" + lottoTickets.size() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
        System.out.println();
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("----------");
        for (LottoRank lottoRank : LottoRank.values()) {
            drawRank(lottoRank);
            System.out.println("- " + lottoResult.countOfRank(lottoRank)+"개");
        }
        System.out.println("총 수익률은 " + lottoResult.earningRate() + "%입니다.");
    }

    private static void drawRank(LottoRank lottoRank) {
        System.out.print(lottoRank.getCountOfMatch()+"개 일치 ");
        System.out.print("(" + lottoRank.getReward() +"원)");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message + "\n");
    }
}