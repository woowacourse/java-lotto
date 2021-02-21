package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.rating.LottoResult;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public OutputView() {
    }

    private static final String NEW_LINE = System.lineSeparator();

    public void printBuyTicket(int count) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, count);
    }

    public void printLottoResults(List<Lotto> lottos) {
        StringBuilder buffer = new StringBuilder();
        for (Lotto lotto : lottos) {
            appendLottoResult(buffer, lotto);
        }
        buffer.append(NEW_LINE);
        System.out.print(buffer.toString());
    }

    public void appendLottoResult(StringBuilder buffer, Lotto lotto) {
        buffer.append("[");
        buffer.append(lotto.getNumbers()
                           .stream()
                           .map(String::valueOf)
                           .collect(Collectors.joining(",")));
        buffer.append("]")
              .append(NEW_LINE);
    }

    public void printWinningStats(RatingCounter ratingCounter, double rate) {
        printWinningDetail(ratingCounter);
        printEarningRate(rate);
    }

    private void printWinningDetail(RatingCounter ratingCounter) {
        System.out.println(NEW_LINE + "당첨 통계" + NEW_LINE + "---------");
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            printRatingResult(rating, ratingCounter.get(rating));
        }
    }

    private void printRatingResult(final Rating rating, final int count) {
        LottoResult lottoResult = rating.getLottoResult();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%d개 일치", lottoResult.getMatchedCount()));
        if (lottoResult.isSecond()) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append(String.format(" (%d원) - %d개", rating.getReward(), count));
        System.out.println(stringBuilder);
    }

    private void printEarningRate(double rate) {
        System.out.printf("총 수익률은 %.2f입니다.", rate);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
