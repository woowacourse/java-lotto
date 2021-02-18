package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.Result;

public class OutputView {
    private OutputView() {
    }

    public static void printResult(Result result) {
        Map<Rank, Integer> resultMap = result.getResultMap();

        System.out.println("당첨 통계");
        System.out.println("---------");

        Arrays.stream(Rank.values())
            .filter(rank -> !Rank.UNRANKED.equals(rank))
            .forEach(rank -> {
                String message = getMessage(rank);
                System.out.printf("%s (%d원)- %d개%n", message, rank.getPrize(), resultMap.get(rank));
            });

        System.out.printf("총 수익률은 %d%%입니다.%n", result.getEarningRate());
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
        System.out.printf("%d개를 구매했습니다.\n", lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
            String numbers = lottoTicket.getUnmodifiableList().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(", "));
            System.out.println("[" + numbers + "]");
        }
        System.out.println();
    }

}
