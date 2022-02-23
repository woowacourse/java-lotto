package lotto.view;

import lotto.model.Lotto;
import lotto.model.Rank;
import lotto.model.ResultMap;

import java.util.*;

public class ResultView {

    public static void printGeneratedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.stream()
                .map(Lotto::getLottoNumbers)
                .forEach(System.out::println);
    }

    public static void printResultStatistics(ResultMap winningResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Arrays.stream(Rank.values())
                .map(rank -> getOneRankStatus(rank, winningResult.getResult().getOrDefault(rank, 0)))
                .forEach(System.out::println);
    }

    private static String getOneRankStatus(Rank rank, Integer winningCount) {
        if (rank == Rank.SECOND) {
            return rank.getValue() + "개 일치, 보너스 볼 일치(" + rank.getMoney() + "원)- " + winningCount + "개";
        }
        return rank.getValue() + "개 일치 (" + rank.getMoney() + "원)- " + winningCount + "개";
    }
}
