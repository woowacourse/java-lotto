package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.Result;

public class OutputView {
    private OutputView() {
    }

    public static void printResult(Result result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        showResults(result);

        System.out.printf("총 수익률은 %s%%입니다.%n", result.getEarningRate());
    }

    private static void showResults(Result result) {
        Arrays.stream(Rank.values())
            .filter(rank -> !Rank.UNRANKED.equals(rank))
            .forEach(rank -> System.out.printf("%s (%s원)- %d개%n",
                getMessage(rank),
                rank.getPrize().toString(),
                result.getResultMap().getOrDefault(rank, 0))
            );
    }

    private static String getMessage(Rank rank) {
        if (Rank.FIRST_PLACE.equals(rank)) {
            return "6개 일치";
        }
        if (Rank.SECOND_PLACE.equals(rank)) {
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

        return "";
    }

    public static void printTickets(List<LottoTicket> lottoTickets, int change) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTickets.size());
        System.out.printf("거스름 돈은 %d원 입니다.\n", change);

        for (LottoTicket lottoTicket : lottoTickets) {
            String numbers = lottoTicket.getUnmodifiableList().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(", "));

            System.out.println("[" + numbers + "]");
        }
        System.out.println();
    }

    public static void printRestart() {
        System.out.println();
        System.out.println("-----");
        System.out.println("프로그램을 다시 시작합니다.");
        System.out.println("-----");
    }

}
