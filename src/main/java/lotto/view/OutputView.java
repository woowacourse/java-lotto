package lotto.view;

import lotto.domain.LottoGroup;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

import java.util.Map;

public class OutputView {

    private static final String LOTTO_COUNT_FORM = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";
    private static final String LOTTO_FORM = "[%s]\n";
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String BOUNDARY = "---------";
    private static final String PROFIT_FORM = "총 수익률은 %.1f%%입니다.\n";
    private static final String LOTTO_RANK_FORM = "%d개 일치%s(%d원) - %d개";

    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printBoughtLotto(final LottoGroup lottoGroup) {
        System.out.println(String.format(LOTTO_COUNT_FORM, lottoGroup.getManualCount(), lottoGroup.getAutoCount()));
        lottoGroup.lottoGroup()
                .forEach(lotto -> System.out.printf(LOTTO_FORM, lotto));
    }

    public static void printLottoResult(final LottoResult lottoResult) {
        System.out.println(RESULT_MESSAGE);
        System.out.println(BOUNDARY);

        lottoResult.rankMatch()
                .entrySet()
                .stream()
                .filter(entrySet -> entrySet.getKey() != LottoRank.NONE)
                .forEach(entrySet -> System.out.println(getLottoRankForm(entrySet)));
        System.out.printf(PROFIT_FORM, lottoResult.winningProfit());
    }

    private static String getLottoRankForm(Map.Entry<LottoRank, Integer> entrySet) {
        if (entrySet.getKey() == LottoRank.SECOND) {
            return String.format(LOTTO_RANK_FORM, entrySet.getKey().matchCount(), ", 보너스 볼 일치", entrySet.getKey().winningMoney(), entrySet.getValue());
        }
        return String.format(LOTTO_RANK_FORM, entrySet.getKey().matchCount(), "", entrySet.getKey().winningMoney(), entrySet.getValue());
    }
}
