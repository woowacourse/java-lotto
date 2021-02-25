package lottogame.view;

import lottogame.domain.statistic.Rank;
import lottogame.domain.dto.LottoDto;
import lottogame.domain.dto.LottoResultsDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void showLottos(int manualQuantity, List<LottoDto> lottosDtos) {
        int autoQuantity = lottosDtos.size() - manualQuantity;
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualQuantity, autoQuantity);
        for (LottoDto lottoDto : lottosDtos) {
            System.out.println(formatLottosOutput(lottoDto.getNumbers()));
        }
    }

    private static String formatLottosOutput(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> String.valueOf(number))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static void printResult(LottoResultsDto lottoResultsDto) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        printSummary(lottoResultsDto.getResults());
        System.out.printf("총 수익률은 %.2f입니다.\n", lottoResultsDto.getProfit());
    }

    private static void printSummary(Map<Rank, Integer> results) {
        for (Map.Entry<Rank, Integer> statistic : results.entrySet()) {
            Rank rank = statistic.getKey();
            int price = statistic.getValue();
            printRank(rank, price);
        }
    }

    private static void printRank(Rank rank, int price) {
        if (rank.isSecond()) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), price);
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), price);
    }
}
