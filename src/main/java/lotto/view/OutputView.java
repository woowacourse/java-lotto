package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.utils.ResultMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.Rank.MISS;

public class OutputView {

    public static void printLottos(List<Lotto> lottos, int countOfSelf) {
        System.out.println("수동으로 " + countOfSelf + "장, 자동으로 " + (lottos.size() - countOfSelf) + "개를 구매했습니다.");
        lottos.stream().forEach(lotto -> System.out.println(lotto));
    }

    public static void printStatistic(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        List<Rank> ranks = Arrays.stream(Rank.values()).filter(rank -> rank != MISS).collect(Collectors.toList());
        ResultMessage.getResult(lottoResult, ranks).stream().forEach(message -> {
            System.out.println(message);
        });
    }

    public static void printYield(double yield) {
        System.out.printf("총 수익룰은 %.1f %%입니다.", yield);
    }

}
