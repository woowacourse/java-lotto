package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.Rank;
import lotto.domain.result.Result;

public class OutputView {

    private OutputView() {
    }

    public static void printResult(Result result) {
        Map<Rank, Integer> resultMap = result.getResultMap();

        System.out.println("당첨 통계");
        System.out.println("---------");

        printResultMap(resultMap);

        System.out.printf("총 수익률은 %s%%입니다.%n", result.getEarningRate());
    }

    private static void printResultMap(Map<Rank, Integer> resultMap) {
        Arrays.stream(Rank.values())
                .filter(rank -> !Rank.UNRANKED.equals(rank))
                .forEach(rank -> {
                    String message = getMessage(rank);
                    extracted(resultMap, rank, message);
                });
    }

    private static void extracted(Map<Rank, Integer> resultMap, Rank rank, String message) {
        String prize = rank.getPrize().toString();
        Integer matchCount = resultMap.getOrDefault(rank, 0);

        System.out.printf("%s (%s원)- %d개%n", message, prize, matchCount);
    }

    private static String getMessage(Rank rank) {
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

        return "";
    }

    public static void printTickets(List<LottoTicket> lottoTickets) {
        printLottoTicketCount(lottoTickets.size());

        for (LottoTicket lottoTicket : lottoTickets) {
            printLottoTicket(lottoTicket);
        }

        System.out.println();
    }

    private static void printLottoTicket(LottoTicket lottoTicket) {
        String numbers = lottoTicket.toUnmodifiableList().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.toInt()))
                .collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }

    private static void printLottoTicketCount(int lottoTicketCount) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTicketCount);
    }
}
