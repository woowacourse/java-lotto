package lotto.view;

import lotto.domain.*;

import java.util.Arrays;

public class OutputView {
    public static void printLottoes(Lottoes lottoes, CustomLottoCount customLottoCount) {
        System.out.println("수동으로 " + customLottoCount.getCustomLottoCount() + "장," +
                " 자동으로" + (lottoes.getSize() - customLottoCount.getCustomLottoCount()) + " 장을 구입했습니다.");
        System.out.println(lottoes);
    }

    public static void printResults(Calculator calculator, Money money) {
        System.out.println("당첨통계");
        System.out.println("------------");
        Arrays.stream(Rank.values())
                .filter(r -> r != Rank.NONE)
                .forEach(r -> {
                    System.out.println(r.getMatchCount() + "개 일치(" + r.getMoney() + ")원-" + calculator.getMatchlottoCountPerRank(r) + "개");
                });
        System.out.println("수익률은 " + calculator.getRate(money) + "%입니다.");
    }
}
