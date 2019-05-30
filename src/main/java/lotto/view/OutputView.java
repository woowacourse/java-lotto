package lotto.view;

import lotto.domain.*;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;

public class OutputView {
    public static void printLottoTickets(LottoTickets lottoTickets) {
        System.out.println(String.format("%d개를 구매했습니다.", lottoTickets.size()));
        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printResult(Result result, Payment payment) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.isMiss())
                .sorted(Comparator.reverseOrder())
                .forEach(rank -> System.out.println(String.format("%d개 일치 (%s원)- %d개", rank.getCountOfMatch(), NumberFormat.getInstance().format(rank.getWinningMoney()), result.get(rank))));

        System.out.println(String.format("총 수익률은 %.2f입니다.",result.calculateEarningsRate(payment)));
    }
}
