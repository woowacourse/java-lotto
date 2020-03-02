package lotto.view.output;

import java.util.Arrays;

import lotto.domain.count.Count;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.money.LottoMoney;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;

public class OutputView {

    private static final String PURCHASE_LOTTO_FORMATTED_STRING = "수동으로 %d장, 자동으로 %d장 구매했습니다.";
    private static final String SECOND_RANK_FORMATTED_STRING = "%d개 일치, 보너스 볼 일치 (%d원) - %d개";
    private static final String RANK_FORMATTED_STRING = "%d개 일치 (%d원) - %d개";
    private static final String PROFIT_FORMATTED_STRING = "총 수익률은 %.0f%%입니다.";

    public static void printLottoTickets(LottoTickets lottoTickets, Count count) {
        System.out.println(String.format(PURCHASE_LOTTO_FORMATTED_STRING, count.getManualCounts(), count.getAutoCounts()));
        lottoTickets.getLottoTickets()
                .forEach(System.out::println);
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("-------");
        Arrays.stream(Rank.values())
                .filter(rank -> rank.isNot(Rank.MISS))
                .forEach(rank ->
                        System.out.println(rankToString(lottoResult, rank)));
    }

    private static String rankToString(LottoResult lottoResult, Rank rank) {
        if (rank == Rank.SECOND) {
            return String.format(SECOND_RANK_FORMATTED_STRING,
                    rank.getCountOfMatches(), rank.getWinningMoney(), lottoResult.count(rank));
        }
        return String.format(RANK_FORMATTED_STRING, rank.getCountOfMatches(), rank.getWinningMoney(), lottoResult.count(rank));
    }

    public static void printProfit(LottoResult lottoResult, LottoMoney lottoMoney) {
        double profit = lottoResult.getProfit(lottoMoney);
        System.out.println(String.format(PROFIT_FORMATTED_STRING, profit));
    }
}
