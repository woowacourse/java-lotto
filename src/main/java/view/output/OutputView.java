package view.output;

import java.util.List;

import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.money.LottoMoney;
import domain.result.LottoResult;
import domain.result.Rank;

public class OutputView {
    public static void printLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicket> lottoTicketList = lottoTickets.getLottoTickets();
        System.out.println("로또를 구매했습니다.");

        lottoTicketList.forEach(System.out::println);
    }

    public static void printLottoResult(LottoResult lottoResult) {
        for (Rank rank : Rank.values()) {
            if (rank == Rank.MISS) {
                continue;
            }
            System.out.println(rankToString(rank) + lottoResult.count(rank) + "개");
        }
    }

    private static String rankToString(Rank rank) {
        if (rank == Rank.SECOND) {
            return rank.getCountOfMatches() + "개 일치, 보너스볼 일 (" + rank.getWinningMoney() + "원) - ";
        }
        return rank.getCountOfMatches() + "개 일치 (" + rank.getWinningMoney() + "원) - ";
    }

    public static void printProfit(LottoResult lottoResult, LottoMoney lottoMoney) {
        double profit = lottoResult.getProfit(lottoMoney);
        System.out.println(String.format("총 수익률은 %.0f%%입니다.", profit));
    }
}
