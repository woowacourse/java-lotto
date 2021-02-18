package lotto.view;

import lotto.domain.*;

import java.util.stream.Collectors;

public class OutputView {

    public static final String LOTTO_FORMAT = "[%s]" + System.lineSeparator();
    public static final String LOTTO_DELIMITER = ", ";
    public static final String RANK_FORMAT = "%d개 일치 (%d원)- %d개";
    public static final String PROFIT_FORM = "총 수익률은 %f2 입니다.";

    public static void printMessage(final Object message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }

    public static void printError(Exception e) {
        printMessage(e.getMessage());
    }

    public static void printEachLotto(final Lottos lottos) {
        lottos.lottos().forEach(OutputView::printOneLotto);
    }

    private static void printOneLotto(Lotto lotto) {
        String joinLottoNumber = lotto.lotto().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(LOTTO_DELIMITER));
        printMessageByFormat(LOTTO_FORMAT, joinLottoNumber);
    }


    public static void printLottoGameResult(final LottoGameResult lottoGameResult) {
        printMessage("당첨 통계");
        printMessage("--------");
        printRankResult(lottoGameResult);
        printMessageByFormat(PROFIT_FORM, lottoGameResult.calculateProfit());
    }

    public static void printRankResult(final LottoGameResult lottoGameResult) {
        lottoGameResult.ranks()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Rank.NOTHING)
                .forEach(entry -> printMessageByFormat(RANK_FORMAT,
                        entry.getKey().getCountOfMatch(),
                        entry.getKey().getReward(),
                        entry.getValue()));
    }
}
