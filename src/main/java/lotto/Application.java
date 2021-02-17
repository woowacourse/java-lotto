package lotto;

import lotto.domain.LottoGroup;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.view.OutputView;

public class Application {

  public static void main(String[] args) {
    LottoGroup lottoGroup = LottoManager.createLotto();
    WinningLotto winningLotto = LottoManager.getWinningLotto();
    LottoManager.printResult(lottoGroup, winningLotto);


//    LottoResult lottoResult = new LottoResult();
//    lottoResult.add(LottoRank.SECOND);
//    OutputView.printLottoResult(lottoResult);
  }
}
