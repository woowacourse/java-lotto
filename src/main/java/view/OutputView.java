package view;

import domain.Lotto;
import domain.LottoRank;
import domain.LottoResult;
import domain.LottoTicket;
import java.util.Map;

public class OutputView {

    public static void printPurchasedLotto(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottos().size() + "개를 구매했습니다.");
        for (Lotto lotto : lottoTicket.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println(
                "당첨 통계\n"
                + "---------");
        Map<LottoRank, Integer> resultCount = lottoResult.getResultCount();
        for (LottoRank rank : resultCount.keySet()) {
            if (rank.equals(LottoRank.RANK_2)) {
                System.out.printf("%d개 일치, 보너스볼 일치(%d원)- %d개\n",
                        rank.getCount(), rank.getPrice(), resultCount.get(rank));
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCount(), rank.getPrice(), resultCount.get(rank));

        }
    }

    public static void printProfitRate(double calculateProfit) {
        System.out.println("총 수익률은 " + calculateProfit + "입니다.");
    }
}
