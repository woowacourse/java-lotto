package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.domain.lottoGroup.MixedLottoGroup;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

  public static void main(String[] args) {
    MixedLottoGroup mixedLottoGroup = InputView.lottoGroup();
    OutputView.printBoughtLotto(mixedLottoGroup);

    WinningLotto winningLotto = InputView.winningLotto();

    LottoResult lottoResult = mixedLottoGroup.lottoResult(winningLotto);
    OutputView.printLottoResult(lottoResult);
  }
}
