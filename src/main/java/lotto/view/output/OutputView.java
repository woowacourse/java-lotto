package lotto.view.output;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.money.LottoMoney;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;

import java.util.Arrays;
import java.util.List;

public class OutputView {

    private static String PURCHASE_LOTTO_FORMATTED_STRING = "로또를 %d장 구매했습니다.";
    private static String SECOND_RANK_FORMATTED_STRING = "%d개 일치, 보너스 볼 일치 (%d원) - %d개";
    private static String RANK_FORMATTED_STRING = "%d개 일치 (%d원) - %d개";
    private static String PROFIT_FORMATTED_STRING = "총 수익률은 %0.f%%입니다.";

    public static void printLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicket> lottoTicketList = lottoTickets.getLottoTickets();
        System.out.println(String.format(PURCHASE_LOTTO_FORMATTED_STRING, lottoTicketList.size()));
        lottoTicketList.forEach(System.out::println);
    }

    public static void printLottoResult(LottoResult lottoResult) {
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
