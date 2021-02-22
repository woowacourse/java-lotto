package lotto.controller;

import lotto.domain.LottoGroup;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

  public static void main(String[] args) {
    LottoGroup lottoGroup = InputView.lottoGroup();
    OutputView.printBoughtLotto(lottoGroup);

    WinningLotto winningLotto = InputView.winningLotto();

    LottoResult lottoResult = lottoGroup.lottoResult(winningLotto);
    OutputView.printLottoResult(lottoResult);
  }
}
