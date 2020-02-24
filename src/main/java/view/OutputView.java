package view;

import domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String COMMA = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String COUNT_UNIT = "개";
    private static final String DASHES = "------------";
    private static final String RESULT_MESSAGE = "개 일치(%d원) - ";
    private static final String RESULT_MESSAGE_FOR_SECOND = "개 일치, 보너스 볼 일치(%d원) - ";
    private static final String INPUT_AMOUNT_GUIDE_MESSAGE = "구매금액을 입력해 주세요";
    private static final String INPUT_MANUAL_COUNT_GUIDE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBER_GUIDE_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String PURCHASE_COUNT_RESULT_MESSAGE = "수동으로 %d장 자동으로 %d개를 구매했습니다.";
    private static final String INPUT_WINNING_NUMBERS_GUIDE_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_GUIDE_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String RESULT_HEADING_MESSAGE = "당첨 통계";
    private static final String PROFIT_RATIO_GUIDE_MESSAGE = "총 수익률은 %d%%입니다.";
    private static final String REQUEST_RE_ENTER_MESSAGE = "다시 입력해 주세요.";

    public static void printInputPurchaseAmountMessage() {
        System.out.println(INPUT_AMOUNT_GUIDE_MESSAGE);
    }

    public static void printInputManualCountMessage() {
        System.out.println(NEW_LINE + INPUT_MANUAL_COUNT_GUIDE_MESSAGE);
    }

    public static void printInputManualLottoNumbersMessage() {
        System.out.println(NEW_LINE + INPUT_MANUAL_LOTTO_NUMBER_GUIDE_MESSAGE);
    }

    public static void printPurchaseCountMessage(LottoCount lottoCount, ManualCount manualCount) {
        System.out.println(NEW_LINE + String.format(PURCHASE_COUNT_RESULT_MESSAGE,
                manualCount.getManualCount(), lottoCount.getAutoLottoCount(manualCount)));
    }

    public static void printLottos() {
        for (Lotto eachLotto : Lottos.getLottos()){
            printEachLotto(eachLotto);
        }
    }

    private static void printEachLotto(final Lotto eachLotto) {
        String lotto = eachLotto.getLotto()
                .stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(COMMA, PREFIX, SUFFIX));
        System.out.println(lotto);
    }

    public static void printInputWinningNumbersMessage() {
        System.out.println(NEW_LINE + INPUT_WINNING_NUMBERS_GUIDE_MESSAGE);
    }

    public static void printInputBonusNumberMessage() {
        System.out.println(INPUT_BONUS_NUMBER_GUIDE_MESSAGE);
    }

    public static void printResult() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NEW_LINE)
                    .append(RESULT_HEADING_MESSAGE)
                    .append(NEW_LINE)
                    .append(DASHES)
                    .append(NEW_LINE);
        printResultDetail(stringBuilder);
    }

    private static void printResultDetail(StringBuilder stringBuilder) {
        List<LottoRank> keys = Arrays.asList(LottoRank.values());
        Collections.reverse(keys);
        for (LottoRank rank : keys){
            stringBuilder.append(rank.getWinningMatchCount());
            resultMessageByRank(stringBuilder, rank);
            stringBuilder.append(LottoResult.getRankCount(rank))
                        .append(COUNT_UNIT)
                        .append(NEW_LINE);
        }
        System.out.println(stringBuilder);
    }

    private static void resultMessageByRank(StringBuilder stringBuilder, LottoRank rank) {
        if (rank == LottoRank.SECOND){
            stringBuilder.append(String.format(RESULT_MESSAGE_FOR_SECOND, rank.getWinningMoney()));
            return;
        }
        stringBuilder.append(String.format(RESULT_MESSAGE, rank.getWinningMoney()));
    }

    public static void printProfitRatio(final int profitRatio) {
        System.out.printf(PROFIT_RATIO_GUIDE_MESSAGE, profitRatio);
    }

    public static void printExceptionMessage(RuntimeException e) {
        System.out.println(e.getMessage());
        System.out.println(REQUEST_RE_ENTER_MESSAGE);
    }
}
