package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printLottoes(Lottoes lottoes) {
        System.out.println(lottoes.getLottoesSize() + "개를 구입했습니다.");
        System.out.println(lottoes.toString());
    }

    public static void printStatistics(Statistics statistics, Money money) {
        System.out.println("당첨통계");
        System.out.println("------------");
        Arrays.stream(Rank.values())
                .filter(r -> r != Rank.NONE)
                .forEach(r -> {
                    System.out.println(r.getMatchCount() + "개 일치(" + r.getMoney() + ")원-" + statistics.getMatchlottoCountPerRank(r) + "개");
                });
        System.out.println("수익률은 "+statistics.getRate(money)+"%입니다.");
    }
}
