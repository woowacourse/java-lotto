package lotto.view;

import lotto.domain.game.Rank;
import lotto.domain.ticket.LottoTickets;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class OutputView {
    static public void printInsertedMoneyInformation(int count, int rest) {
        System.out.println("구매 로또 수: " + count);
        System.out.println("잔돈: " + rest + "원");
    }

    public static void printLottoTicketsInformaion(final LottoTickets lottoTickets) {
        System.out.println("\n번호 리스트");
        System.out.println(lottoTickets.toString());
    }
s

    public static void printGameResult(final Map<Rank, Integer> rankInformation, double winningRate) {
        System.out.println("당첨통계\n------");
        for (Rank rank : rankInformation.keySet()) {
            printRankEach(rank, rankInformation.get(rank));
        }
        System.out.println(new BigDecimal(winningRate).setScale(3, RoundingMode.HALF_EVEN);)
    }

    public static void printRankEach(Rank rank, int nums) {
        System.out.println(rank.getCountOfMatch() + "개 일치 (" + rank.getWinningMoney() + "원) - " + nums + "개");
    }
}
