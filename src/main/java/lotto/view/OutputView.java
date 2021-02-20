package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.primitive.Money;
import lotto.domain.rating.LottoResult;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;
import lotto.domain.statistics.LottoStatistics;

import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public static void getMessage(String message) {
        System.out.println(message);
    }

    public static void printBuyLotto(int count) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, count);
    }

    public static void printLottoResults(LottoRepository lottoRepository) {
        StringBuilder buffer = new StringBuilder();
        for (Lotto lotto : lottoRepository.toList()) {
            appendLottoResult(buffer, lotto);
        }
        buffer.append(NEW_LINE);
        System.out.print(buffer.toString());
    }

    public static void appendLottoResult(StringBuilder buffer, Lotto lotto) {
        buffer.append("[");
        buffer.append(lotto.getNumbers()
                           .stream()
                           .map(String::valueOf)
                           .collect(Collectors.joining(",")));
        buffer.append("]")
              .append(NEW_LINE);
    }

    public static void printWinningStats(LottoStatistics lottoStatistics, int money) {
        printWinningDetail(lottoStatistics);
        printEarningRate(lottoStatistics.getEarningRate(new Money(money)));
    }

    private static void printWinningDetail(LottoStatistics lottoStatistics) {
        System.out.println(NEW_LINE + "당첨 통계" + NEW_LINE + "---------");
        RatingCounter ratingCounter = lottoStatistics.getRatingCounter();
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            printRatingResult(rating, ratingCounter.get(rating));
        }
    }

    private static void printRatingResult(final Rating rating, final int count) {
        LottoResult lottoResult = rating.getLottoResult();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%d개 일치", lottoResult.getMatchedCount()));
        if (lottoResult.isSecond()) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append(String.format(" (%d원) - %d개", rating.getReward(), count));
        System.out.println(stringBuilder.toString());
    }

    private static void printEarningRate(double rate) {
        System.out.printf("총 수익률은 %.2f입니다.", rate);
    }
}
