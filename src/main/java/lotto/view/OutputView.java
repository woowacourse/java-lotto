package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.Rank;

public class OutputView {
    public static void printLottoNumbers(Money money, int numberOfManualLotto) {
        int numberOfAutoLotto = money.getNumberOfLotto() - numberOfManualLotto;
        System.out.printf("\n수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", numberOfManualLotto, numberOfAutoLotto);
    }

    public static void printLottoTickets(LottoTickets lottoTickets) {
        System.out.println(lottoTickets);
    }

    public static void printLottoStatistics(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        printLottoResult(lottoResult);
    }

    private static void printLottoResult(LottoResult lottoResult) {
        for (Rank rank : Rank.values()) {
            System.out.print(getEachResult(lottoResult, rank));
        }
        System.out.printf("총 수익률은 %.1f%%입니다.", lottoResult.getRateOfReturn());
    }

    private static String getEachResult(LottoResult lottoResult, Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", rank.getCountOfMatch(), rank.getWinningMoney(), lottoResult.getResultByRank(rank));
        }
        if (rank.equals(Rank.MISS)) {
            return "";
        }
        return String.format("%d개 일치 (%d원)- %d개\n", rank.getCountOfMatch(), rank.getWinningMoney(), lottoResult.getResultByRank(rank));
    }
}
