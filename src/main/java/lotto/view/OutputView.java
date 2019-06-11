package lotto.view;

import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.Rank;
import lotto.domain.lotto.Result;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;

public class OutputView {
    public static void printResult(Result result, double earningRate) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.isMiss())
                .sorted(Comparator.reverseOrder())
                .forEach(rank -> System.out.println(String.format("%d개 일치 (%s원)- %d개",
                        rank.getCountOfMatch(),
                        NumberFormat.getInstance().format(rank.getWinningMoney()),
                        result.get(rank))));

        System.out.println(String.format("총 수익률은 %.2f입니다.", earningRate));
    }

    public static void printLotto(LottoTickets lottoTickets) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.",
                lottoTickets.getCountOfManualLotto(),
                lottoTickets.getCountOfAutoLotto()));
        lottoTickets.getAllLottoTickets().forEach(System.out::println);
    }
}
