package lotto.view;

import lotto.domain.LottoGroup;

public class OutputView {
  // ~개를 구매했습니다.  OutputView o
  public static final String BUY_FORM = "%d개를 구매했습니다.";
  public static final String LOTTO_FORM = "[%s]\n";

  public static void printMessage(String message) {
    System.out.println(message);
  }

  public static void printBoughtLotto(LottoGroup lottoGroup) {
    lottoGroup.lottoGroup()
        .forEach(lotto -> System.out.printf(LOTTO_FORM, lotto));
  }
}
