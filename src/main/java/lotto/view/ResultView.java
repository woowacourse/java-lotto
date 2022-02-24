package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.vo.Lotto;
import lotto.domain.LottoPrize;
import lotto.domain.LottoResults;

public class ResultView {

    private static final String PURCHASE_RESULT_MESSAGE = "개를 구매했습니다.";
    private static final String PURCHASE_RESULT_DELIMITER = ", ";
    private static final String PURCHASE_RESULT_PRINT_MESSAGE = "[%s]" + System.lineSeparator();

    private static final String LOTTO_RESULT_MESSAGE = "당첨 통계";
    private static final String LOTTO_RESULT_DELIMITER = "---------";
    private static final String LOTTO_RESULT_TWICE_MESSAGE = "%d개, 보너스 볼 일치 일치 (%d원)- %d개" + System.lineSeparator();
    private static final String LOTTO_RESULT_DEFAULT_MESSAGE = "%d개 일치 (%d원)- %d개" + System.lineSeparator();
    private static final String LOTTO_RATE_RETURN_MESSAGE = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    private static final String EMPTY_MESSAGE = "";
    private static final int HUNDRED_FOR_FLOOR = 100;

    public static void printLottos(List<Lotto> lottos) {
        int size = lottos.size();
        System.out.println(size + PURCHASE_RESULT_MESSAGE);
        for (Lotto lotto : lottos) {
            String numbers = lotto.getNumbers().stream()
                    .map(lottoNumber -> Integer.toString(lottoNumber.get()))
                    .collect(Collectors.joining(PURCHASE_RESULT_DELIMITER));
            System.out.printf(PURCHASE_RESULT_PRINT_MESSAGE, numbers);
        }
        System.out.println();
    }

    public static void printResults(LottoResults lottoResults) {
        System.out.println(LOTTO_RESULT_MESSAGE);
        System.out.println(LOTTO_RESULT_DELIMITER);
        for (LottoPrize prize : LottoPrize.values()) {
            int number = lottoResults.getPrizeNumber(prize);
            System.out.print(generateLottoResultMessage(prize, number));
        }
        double rateReturn = Math.floor(lottoResults.getRateReturn() * HUNDRED_FOR_FLOOR) / HUNDRED_FOR_FLOOR;
        System.out.printf(LOTTO_RATE_RETURN_MESSAGE, rateReturn);
    }

    private static String generateLottoResultMessage(LottoPrize prize, int number) {
        if (prize == LottoPrize.MISS) {
            return EMPTY_MESSAGE;
        }
        if (prize == LottoPrize.TWICE) {
            return String
                    .format(LOTTO_RESULT_TWICE_MESSAGE, prize.getLottoNumberMatches(), prize.getReward().get(), number);
        }
        return String
                .format(LOTTO_RESULT_DEFAULT_MESSAGE, prize.getLottoNumberMatches(), prize.getReward().get(), number);
    }
}
