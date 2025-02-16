package view;

import dto.LottoDto;
import dto.LottosDto;
import dto.StatisticsDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import domain.PrizeTier;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매하였습니다.";
    private static final String STATISTICS_HEADER_MESSAGE = "\n당첨 통계\n---------";
    private static final String STATISTICS_FORMAT = "%d개 일치%s (%d원)- %d개\n";
    private static final String BONUS_MATCHED_MESSAGE = ", 보너스 볼 일치";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.2f입니다.";

    public void printLottos(LottosDto lottosDto) {
        List<LottoDto> lottoDtos = lottosDto.getLottoDtos();
        System.out.printf("%d%s\n", lottoDtos.size(), PURCHASE_COUNT_MESSAGE);
        printLottoNumbers(lottoDtos);
        System.out.println();
    }

    private void printLottoNumbers(List<LottoDto> lottoDtos) {
        for (LottoDto lottoDto : lottoDtos) {
            List<Integer> numbers = lottoDto.getNumbers();
            System.out.println(numbers.toString());
        }
    }

    public void printStatistics(StatisticsDto statisticsDto) {
        System.out.println(STATISTICS_HEADER_MESSAGE);
        Map<PrizeTier, Integer> prizeCounts = statisticsDto.getPrizeCounts();
        PrizeTier[] prizeTiers = PrizeTier.values();
        Arrays.sort(prizeTiers, Collections.reverseOrder());
        printPrizeTiers(prizeTiers, prizeCounts);
        System.out.printf(PROFIT_RATE_FORMAT, statisticsDto.getProfitRate());
    }

    private void printPrizeTiers(PrizeTier[] prizeTiers, Map<PrizeTier, Integer> prizeCounts) {
        for (PrizeTier prizeTier : prizeTiers) {
            printPrizeTier(prizeCounts, prizeTier);
        }
    }

    private void printPrizeTier(Map<PrizeTier, Integer> prizeCounts, PrizeTier prizeTier) {
        int matchedCount = prizeTier.getMatchedCount();
        int prize = prizeTier.getPrize();
        int prizeTierCount = prizeCounts.get(prizeTier);

        if (prizeTier == PrizeTier.SECOND) {
            System.out.printf(STATISTICS_FORMAT, matchedCount, BONUS_MATCHED_MESSAGE, prize,
                prizeTierCount);
            return;
        }
        if (prizeTier != PrizeTier.NONE) {
            System.out.printf(STATISTICS_FORMAT, matchedCount, "", prize, prizeTierCount);
        }
    }
}
