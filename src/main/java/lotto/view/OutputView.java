package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.HashMap;
import java.util.Map;

import static lotto.domain.Rank.MISS;
import static lotto.domain.Rank.SECOND;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.getLottosSize() + "개를 구매했습니다.");
        for (int i = 0; i < lottos.getLottosSize(); i++) {
            System.out.println(lottos.getLotto(i));
        }
    }

    public static void printStatistic(Map<Rank,Integer> results) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        results.keySet().stream().filter( rank -> rank != SECOND).forEach(rank -> System.out.println(rank.getResultMessage() + results.get(rank) + "개"));
    }

    public static void printYeild(double yield) {
        System.out.printf("총 수익룰은 %.1f %%입니다.",yield);
    }
}
