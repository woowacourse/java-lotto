package lotto.view;

import lotto.domain.*;

import java.util.stream.Collectors;

public class OutputView {

    public static final String LOTTO_PURCHASE_FORM = "수동으로 %d장, 자동으로 %d개를 구매했습니다." + System.lineSeparator();
    public static final String LOTTO_DELIMITER = ", ";
    public static final String LOTTO_FORM = "[%s]" + System.lineSeparator();
    public static final String PROFIT_FORM = "총 수익률은 %.2f 입니다." + System.lineSeparator();
    public static final String GAME_RESULT = "당첨 통계";
    public static final String SECTION_DELIMITER = "--------";


    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }

    public static void printError(String errorMessage) {
        printMessage(System.lineSeparator() + errorMessage + System.lineSeparator());
    }

    public static void printEachLotto(final Lottos manualLottos,final Lottos autoLottos) {
        manualLottos.toList().forEach(OutputView::printOneLotto);
        autoLottos.toList().forEach(OutputView::printOneLotto);
        System.out.println();
    }

    public static void printTotalNumberOfLotto(Lottos manualLottos, Lottos AutoLottos) {
        printMessageByFormat(LOTTO_PURCHASE_FORM, manualLottos.toList().size(), AutoLottos.toList().size());
    }

    private static void printOneLotto(Lotto lotto) {
        String joinLottoNumber = lotto.toList().stream()
                .map(LottoNumber::getStringNumber)
                .collect(Collectors.joining(LOTTO_DELIMITER));

        printMessageByFormat(LOTTO_FORM, joinLottoNumber);
    }

    public static void printLottoGameResult(final LottoGameResult lottoGameResult) {
        printMessage(System.lineSeparator() + GAME_RESULT);
        printMessage(SECTION_DELIMITER);

        printRankResult(lottoGameResult);

        printMessageByFormat(PROFIT_FORM, lottoGameResult.calculateProfit());
    }

    public static void printRankResult(final LottoGameResult lottoGameResult) {
        lottoGameResult.toResultMap()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Rank.NOTHING)
                .forEach(entry -> printMessage(entry.getKey().rankMessage(entry.getValue())));
    }
}
