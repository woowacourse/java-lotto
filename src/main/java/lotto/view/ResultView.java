package lotto.view;

import static lotto.domain.vo.Lotto.LOTTO_PRICE;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.vo.Lotto;
import lotto.domain.LottoPrize;
import lotto.dto.ResponsePurchaseResultsDto;
import lotto.dto.ResponseWinningResultsDto;

public class ResultView {

    private static final String PURCHASE_RESULT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다." + System.lineSeparator();
    private static final String PURCHASE_RESULT_DELIMITER = ", ";
    private static final String PURCHASE_RESULT_PRINT_MESSAGE = "[%s]" + System.lineSeparator();

    private static final String LOTTO_RESULT_MESSAGE = "당첨 통계";
    private static final String LOTTO_RESULT_DELIMITER = "---------";
    private static final String LOTTO_RESULT_TWICE_MESSAGE = "%d개, 보너스 볼 일치 일치 (%d원)- %d개" + System.lineSeparator();
    private static final String LOTTO_RESULT_DEFAULT_MESSAGE = "%d개 일치 (%d원)- %d개" + System.lineSeparator();
    private static final String LOTTO_RATE_RETURN_MESSAGE = "총 수익률은 %.2f입니다.";
    private static final String RATE_RETURN_LOSS_MESSAGE = "(손해입니다.)";
    private static final String RATE_RETURN_BENEFIT_MESSAGE = "(이득입니다.)";
    private static final String RATE_RETURN_BREAK_EVEN_MESSAGE = "(본전입니다.)";
    private static final int RATE_RETURN_FLOOR_SEPARATOR = 2;
    private static final double RATE_RETURN_STANDARD = 1.0;

    private static final String EMPTY_MESSAGE = "";

    public static void printPurchaseLottos(ResponsePurchaseResultsDto dto) {
        System.out.printf(PURCHASE_RESULT_MESSAGE, dto.getManualLottoCount(), dto.getAutoLottoCount());

        for (Lotto lotto : dto.getLottos()) {
            String numbers = lotto.getNumbers().stream()
                    .map(lottoNumber -> Integer.toString(lottoNumber.get()))
                    .collect(Collectors.joining(PURCHASE_RESULT_DELIMITER));
            System.out.printf(PURCHASE_RESULT_PRINT_MESSAGE, numbers);
        }
        System.out.println();
    }

    public static void printResults(ResponseWinningResultsDto dto) {
        System.out.println(LOTTO_RESULT_MESSAGE);
        System.out.println(LOTTO_RESULT_DELIMITER);

        Map<LottoPrize, Integer> results = dto.getResults();
        for (LottoPrize prize : LottoPrize.values()) {
            int number = results.get(prize);
            System.out.print(generateLottoResultMessage(prize, number));
        }

        System.out.println(generateRateReturnMessage(calculateRateReturn(results)));
    }

    private static double calculateRateReturn(Map<LottoPrize, Integer> results) {
        int totalSpendMoney = getTotalSpendMoney(results);
        int totalReward = getTotalReward(results);
        double rateReturn = (double) totalReward / totalSpendMoney;
        return floor(rateReturn, RATE_RETURN_FLOOR_SEPARATOR);
    }

    private static int getTotalSpendMoney(Map<LottoPrize, Integer> results) {
        int totalSpendMoney = 0;
        for (LottoPrize prize : LottoPrize.values()) {
            int count = results.get(prize);
            totalSpendMoney += count * LOTTO_PRICE.get();
        }
        return totalSpendMoney;
    }

    private static int getTotalReward(Map<LottoPrize, Integer> results) {
        int totalReward = 0;
        for (LottoPrize prize : LottoPrize.values()) {
            int count = results.get(prize);
            totalReward += prize.getTotalReward(count);
        }
        return totalReward;
    }

    private static double floor(double number, int separator) {
        double separatorNumber = Math.pow(10, separator);
        return Math.floor(number * separatorNumber) / separatorNumber;
    }

    private static String generateLottoResultMessage(LottoPrize prize, int number) {
        if (prize == LottoPrize.MISS) {
            return EMPTY_MESSAGE;
        }
        if (prize == LottoPrize.TWICE) {
            return String.format(
                    LOTTO_RESULT_TWICE_MESSAGE, prize.getLottoNumberMatchCount(), prize.getReward().get(), number);
        }
        return String.format(
                LOTTO_RESULT_DEFAULT_MESSAGE, prize.getLottoNumberMatchCount(), prize.getReward().get(), number);
    }

    private static String generateRateReturnMessage(double rateReturn) {
        if (rateReturn > RATE_RETURN_STANDARD) {
            return String.format(LOTTO_RATE_RETURN_MESSAGE + RATE_RETURN_BENEFIT_MESSAGE, rateReturn);
        }
        if (rateReturn < RATE_RETURN_STANDARD) {
            return String.format(LOTTO_RATE_RETURN_MESSAGE + RATE_RETURN_LOSS_MESSAGE, rateReturn);
        }
        return String.format(LOTTO_RATE_RETURN_MESSAGE + RATE_RETURN_BREAK_EVEN_MESSAGE, rateReturn);
    }
}
