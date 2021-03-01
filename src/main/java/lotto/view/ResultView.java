package lotto.view;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import lotto.domain.lotto.Rank;
import lotto.domain.result.Result;

public class ResultView {

    private ResultView() {
    }

    public static void printResult(Result result) {
        Map<Rank, Integer> resultMap = result.getResultMap();

        System.out.println("\n당첨 통계");
        System.out.println("---------");

        printResultMap(resultMap);

        System.out.printf("총 수익률은 %s%%입니다.\n", result.getEarningRate());
    }

    private static void printResultMap(Map<Rank, Integer> resultMap) {
        Arrays.stream(Rank.values())
                .filter(rank -> !Rank.UNRANKED.equals(rank))
                .forEach(rank -> printResultPerRank(resultMap, rank));
    }

    private static void printResultPerRank(Map<Rank, Integer> resultMap, Rank rank) {
        String message = getRankMessage(rank);
        BigInteger prize = rank.getPrize();
        Integer matchCount = resultMap.getOrDefault(rank, 0);

        System.out.printf("%s (%s원)- %d개%n", message, prize, matchCount);
    }

    private static String getRankMessage(Rank rank) {
        if (Rank.FIRST_PLACE.equals(rank)) {
            return "6개 일치";
        }
        if (Rank.SEC0ND_PLACE.equals(rank)) {
            return "5개 일치, 보너스 볼 일치";
        }
        if (Rank.THIRD_PLACE.equals(rank)) {
            return "5개 일치";
        }
        if (Rank.FOURTH_PLACE.equals(rank)) {
            return "4개 일치";
        }
        if (Rank.FIFTH_PLACE.equals(rank)) {
            return "3개 일치";
        }

        throw new IllegalArgumentException("UNRANKED는 출력하지 않습니다.");
    }
}
