package lotto.view;

import lotto.domain.lottoTicket.Lottos;
import lotto.domain.rank.Rank;

public class OutputView {
    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }

    public static void printRankState(Rank rank) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(rank.toString());
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + (int) rateOfReturn + "%입니다.");
    }
}
