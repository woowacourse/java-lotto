package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoBuyCount;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottosDto;
import lotto.domain.Money;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String RANK_RESULT_FORMAT = "%d개 일치(%d원) - %d개\n";
    private static final String SECOND_RANK_RESULT_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개\n";
    private static final String YIELD_MESSAGE_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";

    private OutputView() {
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printCount(LottoBuyCount lottoBuyCount) {
        System.out.printf(PURCHASE_COUNT_MESSAGE, lottoBuyCount.getManualCount(), lottoBuyCount.getAutoCount());
    }

    public static void printLotto(LottosDto lottosDto) {
        for (Lotto lotto : lottosDto.getLotto()) {
            System.out.println(lotto);
        }
    }

    public static void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        printRankResult(result);
    }

    private static void printRankResult(LottoResult result) {
        result.getResult().entrySet().stream()
                .filter(lottoResult -> lottoResult.getKey() != LottoRank.NOTHING)
                .forEach(lottoResult -> printEachRank(lottoResult.getKey(), lottoResult.getValue()));
    }

    private static void printEachRank(LottoRank lottoRank, Integer count) {
        if (lottoRank == LottoRank.SECOND) {
            System.out.printf(SECOND_RANK_RESULT_FORMAT, lottoRank.getCorrectNumber(), lottoRank.getPrizeAmount(),
                    count);
            return;
        }
        System.out.printf(RANK_RESULT_FORMAT, lottoRank.getCorrectNumber(), lottoRank.getPrizeAmount(), count);
    }

    public static void printYield(double yield) {
        System.out.printf(YIELD_MESSAGE_FORMAT, yield);
    }
}
