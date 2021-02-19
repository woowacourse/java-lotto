package lotto.controller;

import lotto.domain.LottoGroup;
import lotto.domain.WinningLotto;

public class Application {

  public static void main(String[] args) {
    LottoGroup lottoGroup = LottoManager.createRandomLotto();
    WinningLotto winningLotto = LottoManager.getWinningLotto();
    LottoManager.printResult(lottoGroup, winningLotto);
  }
}
