package lotto.view;

import lotto.domain.*;

import java.util.stream.Collectors;

public class OutputView {

    public static final String LOTTO_PURCHASE_FORM = "%d개를 구매했습니다." + System.lineSeparator();
    public static final String LOTTO_DELIMITER = ", ";
    public static final String LOTTO_FORM = "[%s]" + System.lineSeparator();
    public static final String PROFIT_FORM = "총 수익률은 %.2f 입니다." + System.lineSeparator();
    public static final String RANK_FORM = "%d개 일치 (%d원)- %d개" + System.lineSeparator();
    public static final String RANK_BONUS_FORM = "%d개 일치, 보너스 볼 일치(%d원)- %d개" + System.lineSeparator();


    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }

    public static void printError(String errorMessage) {
        printMessage(System.lineSeparator() + errorMessage + System.lineSeparator());
    }

    public static void printEachLotto(final Lottos lottos) {
        lottos.toList().forEach(OutputView::printOneLotto);
        System.out.println();
    }

    public static void printTotalNumberOfLotto(Lottos lottos) {
        printMessageByFormat(LOTTO_PURCHASE_FORM, lottos.toList().size());
    }

    private static void printOneLotto(Lotto lotto) {
        String joinLottoNumber = lotto.lotto().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(LOTTO_DELIMITER));
        printMessageByFormat(LOTTO_FORM, joinLottoNumber);
    }


    public static void printLottoGameResult(final LottoGameResult lottoGameResult) {
        printMessage(System.lineSeparator() + "당첨 통계");
        printMessage("--------");
        printRankResult(lottoGameResult);
        printMessageByFormat(PROFIT_FORM, lottoGameResult.calculateProfit());
    }

    public static void printRankResult(final LottoGameResult lottoGameResult) {
        lottoGameResult.ranks()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Rank.NOTHING)
                .forEach(entry -> printMessageByFormat(RANK_FORM,
                        entry.getKey().getCountOfMatch(),
                        entry.getKey().getReward(),
                        entry.getValue()));
    }
}
