package lotto;

import lotto.domain.LottoGroup;
import lotto.domain.WinningLotto;
import lotto.util.LottoManager;

public class Application {

  public static void main(String[] args) {
    LottoGroup lottoGroup = LottoManager.createRandomLotto();
    WinningLotto winningLotto = LottoManager.getWinningLotto();
    LottoManager.printResult(lottoGroup, winningLotto);
  }
}
