package lotto.util;

import lotto.domain.LottoGroup;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoManager {

  public static LottoGroup createRandomLotto() {
    LottoGroup lottoGroup = InputView.randomLottoGroup();
    OutputView.printBoughtLotto(lottoGroup);

    return lottoGroup;
  }

  public static WinningLotto getWinningLotto() {
    return InputView.winningLotto();
  }

  public static void printResult(LottoGroup lottoGroup, WinningLotto winningLotto) {
    LottoResult lottoResult = new LottoResult();
    lottoGroup.lottoGroup()
        .stream()
        .map(winningLotto::matchRank)
        .forEach(lottoResult::add);
    OutputView.printLottoResult(lottoResult);
  }

}
