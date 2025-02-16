package lotto.view;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Rank;

public class OutputView {

    public void lottoQuantityPrint(int lottoQuantity) {
        System.out.println(lottoQuantity + "개를 구매했습니다.");
    }

    public void lottoStatusPrint(List<List<Integer>> lottoBundle) {
        for (List<Integer> lottoNumbers : lottoBundle) {
            String numbers = lottoNumbers.stream()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            System.out.println("[" + numbers + "]");
        }
        System.out.println();
    }


    public void lottoStatisticsPrint(EnumMap<Rank, Integer> statistics, String profitRate) {

        System.out.println();
        System.out.println("당첨통계");
        System.out.println("---------");
        System.out.println("3개 일치 (" + Rank.FIFTH_PRIDE.getPrize() + "원)- " + statistics.get(Rank.FIFTH_PRIDE) + "개");
        System.out.println("4개 일치 (" + Rank.FOURTH_PRIDE.getPrize() + "원)- " + statistics.get(Rank.FOURTH_PRIDE) + "개");
        System.out.println("5개 일치 (" + Rank.THIRD_PRIDE.getPrize() + "원)- " + statistics.get(Rank.THIRD_PRIDE) + "개");
        System.out.println("5개 일치 (" + Rank.SECOND_PRIDE.getPrize() + "원)- " + statistics.get(Rank.SECOND_PRIDE) + "개");
        System.out.println("6개 일치 (" + Rank.FIRST_PRIDE.getPrize() + "원)- " + statistics.get(Rank.FIRST_PRIDE) + "개");

        System.out.println("총 수익률은 " + profitRate + "입니다.");

    }

    public void errorMessagePrint(String message) {
        System.out.println(message);
    }
}
