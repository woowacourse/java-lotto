package lotto.view;

import lotto.domain.*;

public class OutputView {
    public static void printLottoTickets(LottoCount lottoManualCount, LottoTickets lottoTickets) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d장을 구매했습니다.",
                lottoManualCount.getCount(), lottoTickets.size() - lottoManualCount.getCount()));
        System.out.println(lottoTickets.toString());
    }

    public static void printWinningLottoState(WinningLottoState winningLottoState, Money money) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        StringBuilder sb = new StringBuilder();
        for (Rank rank : winningLottoState.getWinningLottoStateKeySet()) {
            sb.append(String.format("%d개 일치 ", rank.getCountOfMatch()));
            if (rank.getMatchBonusNo()) {
                sb.append("보너스 볼 일치");
            }
            sb.append(String.format("(%d원) - %d개\n", rank.getWinningMoney(), winningLottoState.countOfRank(rank)));
        }
        sb.append(String.format("총 수익률은 %.2f%% 입니다.", winningLottoState.getYield(money)));
        System.out.println(sb.toString());
    }
}
