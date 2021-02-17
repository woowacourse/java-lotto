package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.LottoResult;

public class OutputView {

  // ~개를 구매했습니다.  OutputView o
  private static final String BUY_FORM = "%d개를 구매했습니다.";
  private static final String LOTTO_FORM = "[%s]\n";
  private static final String RESULT_MESSAGE = "당첨 통계";
  private static final String BOUNDARY = "---------";
  private static final String PROFIT_FORM = "총 수익률은 %.1f%%입니다.\n";

  public static void printMessage(String message) {
    System.out.println(message);
  }

  public static void printBoughtLotto(LottoGroup lottoGroup) {
    lottoGroup.lottoGroup()
        .forEach(lotto -> System.out.printf(LOTTO_FORM, lotto));
  }

  public static void printLottoResult(LottoResult lottoResult) {
    System.out.println(RESULT_MESSAGE);
    System.out.println(BOUNDARY);

    lottoResult.rankMatch()
        .forEach((key, value) -> System.out.println(key.message(value)));
    System.out.printf(PROFIT_FORM, lottoResult.winningProfit());
  }
}
