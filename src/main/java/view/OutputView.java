package view;

import domain.lotto.Lotto;
import domain.lotto.LottoBundle;
import domain.result.LottoRank;
import domain.result.LottoResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_INDICATOR = "[ERROR] ";
    private static final String GAME_MONEY_REQUEST = "구입금액을 입력해 주세요.";
    private static final String MANUAL_LOTTO_AMOUNT_REQUEST = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_REQUEST = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String LOTTO_BOUGHT_STATUS = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
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

    public static void printManualLottoAmountRequest() {
        printNewLine();
        System.out.println(MANUAL_LOTTO_AMOUNT_REQUEST);
    }

    public static void printManualLottoRequest() {
        printNewLine();
        System.out.println(MANUAL_LOTTO_REQUEST);
    }

    public static void printLottoBought(final LottoBundle manualLottoBundle, final LottoBundle autoLottoBundle) {
        printNumberOfLottoBought(manualLottoBundle, autoLottoBundle);
        for (Lotto manualLotto : manualLottoBundle.getLottoBundle()) {
            printSingleLottoBought(manualLotto);
        }
        for (Lotto autoLotto : autoLottoBundle.getLottoBundle()) {
            printSingleLottoBought(autoLotto);
        }
        printNewLine();
    }

    private static void printNumberOfLottoBought(final LottoBundle manualLottoBundle, final LottoBundle autoLottoBundle) {
        final int manualLottoBought = manualLottoBundle.countNumberOfLotto();
        final int autoLottoBought = autoLottoBundle.countNumberOfLotto();
        printNewLine();
        System.out.println(String.format(LOTTO_BOUGHT_STATUS, manualLottoBought, autoLottoBought));
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

    public static void printLottoResult(final LottoResult lottoResult) {
        printNewLine();
        System.out.println(LOTTO_RESULT_STATUS);
        System.out.println(LOTTO_RESULT_LINE);
        printLottoRankResult(lottoResult.getLottoResult());
    }

    private static void printLottoRankResult(final Map<LottoRank, Integer> lottoRank) {
        System.out.println(String.format(LOTTO_RESULT_THREE_MATCH, lottoRank.get(LottoRank.THREE_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_FOUR_MATCH, lottoRank.get(LottoRank.FOUR_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_FIVE_MATCH, lottoRank.get(LottoRank.FIVE_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_FIVE_AND_BONUS_MATCH, lottoRank.get(LottoRank.FIVE_AND_BONUS_MATCHES)));
        System.out.println(String.format(LOTTO_RESULT_SIX_MATCH, lottoRank.get(LottoRank.SIX_MATCHES)));
    }

    public static void printProfitRate(final double profitRate) {
        System.out.println(String.format(PROFIT_RATE_FORMAT, profitRate));
    }

    private static void printNewLine() {
        System.out.println();
    }
}
