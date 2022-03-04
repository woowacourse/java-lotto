package view;

import static java.lang.System.out;
import static model.LottoRank.SECOND;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.LottoNumber;
import model.LottoOrder;
import model.LottoRank;
import model.dto.LottoDto;
import model.dto.WinningStatisticsDto;

public class OutputView {
    public static void printErrorMessage(String message) {
        out.println(message);
    }

    public static void printPurchasedLotteries(LottoOrder lottoOrder, List<LottoDto> lotteriesDto) {
        out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.%n", lottoOrder.getManualLottoCount(), lottoOrder.getAutoLottoCount());
        lotteriesDto.forEach(OutputView::printLottoNumbers);
    }

    public static void printStatistics(WinningStatisticsDto winningStatistics) {
        out.printf("당첨 통계%n---------%n");
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> printWinningResult(lottoRank, winningStatistics.getWinningCounts().get(lottoRank)));
        printEarningsResult(winningStatistics.getEarningsRate());
    }

    private static void printLottoNumbers(LottoDto lotto) {
        List<String> lottoNumbers = lotto.getLottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.toUnmodifiableList());
        out.printf("[%s]%n", String.join(", ", lottoNumbers));
    }

    private static void printWinningResult(LottoRank lottoRank, int count) {
        if (lottoRank == SECOND) {
            out.printf("%s개 일치, 보너스 볼 일치 (%s원) - %s개%n", SECOND.getWinningNumberCount(),
                    SECOND.getPrizeMoney(), count);
            return;
        }
        out.printf("%s개 일치 (%s원) - %s개%n", lottoRank.getWinningNumberCount(), lottoRank.getPrizeMoney(),
                count);
    }

    private static void printEarningsResult(double earningsRate) {
        String result = String.format("총 수익률은 %.2f 입니다.", earningsRate);
        if (earningsRate < 1) {
            result = String.format("%s(기준이 1이기 때문에 결과적으로 손해라는 의미임)", result);
        }
        out.println(result);
    }
}
