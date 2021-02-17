package lotto.view;

import lotto.domain.LottoGroup;
import lotto.domain.LottoRank;
import lotto.util.LottoResult;

public class OutputView {

  private static final String LOTTO_FORM = "[%s]\n";
  private static final String RESULT_MESSAGE = "당첨 통계";
  private static final String BOUNDARY = "---------";
  private static final String PROFIT_FORM = "총 수익률은 %.1f%%입니다.\n";

  public static void printMessage(final String message) {
    System.out.println(message);
  }

  public static void printBoughtLotto(final LottoGroup lottoGroup) {
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
        .forEach(entrySet -> System.out.println(entrySet.getKey().message(entrySet.getValue())));
    System.out.printf(PROFIT_FORM, lottoResult.winningProfit());
  }
}
