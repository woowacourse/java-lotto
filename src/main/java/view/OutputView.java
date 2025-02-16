package view;

import java.util.Map.Entry;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoStatistics;
import model.LottoWinRank;
import model.PurchasedLottos;

public class OutputView {
    public void printPurchaseResult(Integer purchaseCount) {
        System.out.printf("%d개를 구매했습니다.%n", purchaseCount);
    }

    public void printPurchasedLottos(PurchasedLottos purchasedLottos) {
        purchasedLottos.getLottos().forEach(this::printLottoNumbers);
    }

    private void printLottoNumbers(LottoNumbers lottoNumbers) {
        System.out.println(lottoNumbers.getNumbers().stream().map(LottoNumber::getNumber).toList());
    }

    public void printResult(LottoStatistics lottoStatistics) {
        for (Entry<LottoWinRank, Integer> statisticsEntry : lottoStatistics.getStatisticsEntries()) {
            LottoWinRank lottoWinRank = statisticsEntry.getKey();
            Integer count = statisticsEntry.getValue();
            printLottoWinStatistics(lottoWinRank, count);
        }
    }

    public void printTotalReturn(LottoStatistics lottoStatistics, PurchasedLottos purchasedLottos) {
        System.out.printf("총 수익률은 %.2f입니다.%n", lottoStatistics.totalReturn(purchasedLottos.getPurchaseAmount()));
    }

    private void printLottoWinStatistics(LottoWinRank lottoWinRank, Integer count) {
        if (lottoWinRank.matchBonusNumber()) {
            printLottoWinStatisticsWithBonusBall(lottoWinRank, count);
        }
        System.out.printf("%d개 일치 (%d원)- %d개%n", lottoWinRank.getMatchNumberCount(), lottoWinRank.getPrice(),
                nullToZero(count));
    }

    private void printLottoWinStatisticsWithBonusBall(LottoWinRank lottoWinRank, Integer count) {
        System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n", lottoWinRank.getMatchNumberCount(), lottoWinRank.getPrice(),
                nullToZero(count));
    }

    private Integer nullToZero(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }
}
