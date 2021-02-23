package view;

import domain.Lotto;
import domain.LottoBundle;
import domain.LottoRank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_INDICATOR = "[ERROR] ";
    private static final String GAME_MONEY_REQUEST = "구입금액을 입력해 주세요.";
    private static final String LOTTO_BOUGHT_STATUS = "%d개를 구매했습니다.";
    private static final String LOTTO_BOUGHT_PRINT_PREFIX = "[";
    private static final String LOTTO_BOUGHT_PRINT_DELIMITER = ", ";
    private static final String LOTTO_BOUGHT_PRINT_POSTFIX = "]";
    private static final String WINNING_LOTTO_REQUEST = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_REQUEST = "보너스 볼을 입력해 주세요.";
    private static final String LOTTO_RESULT_STATUS = "당첨 통계";
    private static final String LOTTO_RESULT_LINE = "---------";
    private static final String LOTTO_RESULT_THREE_MATCH = "3개 일치 (5000원)- %d개";
    private static final String LOTTO_RESULT_FOUR_MATCH = "4개 일치 (50000원)- %d개";
    private static final String LOTTO_RESULT_FIVE_MATCH = "5개 일치 (1500000원)- %d개";
    private static final String LOTTO_RESULT_FIVE_AND_BONUS_MATCH = "5개 일치, 보너스 볼 일치(30000000원) - %d개";
    private static final String LOTTO_RESULT_SIX_MATCH = "6개 일치 (2000000000원)- %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.2f입니다.";

    private OutputView() {
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(ERROR_INDICATOR + errorMessage);
    }

    public static void printGameMoneyRequest() {
        System.out.println(GAME_MONEY_REQUEST);
    }

    public static void printLottoBought(final LottoBundle lottoBundle) {
        printNumberOfLottoBought(lottoBundle);
        for(Lotto lotto : lottoBundle.getLottoBundle()) {
            printSingleLottoBought(lotto);
        }
        printNewLine();
    }

    private static void printNumberOfLottoBought(final LottoBundle lottoBundle) {
        final int lottoBoughtNumber = lottoBundle.countNumberOfLotto();
        System.out.println(String.format(LOTTO_BOUGHT_STATUS, lottoBoughtNumber));
    }

    private static void printSingleLottoBought(final Lotto lotto) {
        List<String> singleLottoBought = lotto.getLotto()
                .stream()
                .map(lottoBall -> String.valueOf(lottoBall.getNumber()))
                .collect(Collectors.toList());

        System.out.println(LOTTO_BOUGHT_PRINT_PREFIX +
                String.join(LOTTO_BOUGHT_PRINT_DELIMITER, singleLottoBought) +
                LOTTO_BOUGHT_PRINT_POSTFIX);
    }

    public static void printWinningLottoRequest() {
        System.out.println(WINNING_LOTTO_REQUEST);
    }


    public static void printBonusBallRequest() {
        System.out.println(BONUS_BALL_REQUEST);
    }

    public static void printLottoResult(final Map<LottoRank, Integer> lottoResult) {
        printNewLine();
        System.out.println(LOTTO_RESULT_STATUS);
        System.out.println(LOTTO_RESULT_LINE);
        System.out.println(String.format(LOTTO_RESULT_THREE_MATCH, lottoResult.get(LottoRank.THREE_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_FOUR_MATCH, lottoResult.get(LottoRank.FOUR_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_FIVE_MATCH, lottoResult.get(LottoRank.FIVE_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_FIVE_AND_BONUS_MATCH, lottoResult.get(LottoRank.FIVE_AND_BONUS_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_SIX_MATCH, lottoResult.get(LottoRank.SIX_MATCHES)));
    }

    public static void printProfitRate(final double profitRate) {
        System.out.println(String.format(PROFIT_RATE_FORMAT, profitRate));
    }

    private static void printNewLine() {
        System.out.println();
    }
}
