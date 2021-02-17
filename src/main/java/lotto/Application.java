package lotto;

import lotto.domain.LottoGroup;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
  public static void main(String[] args) {

    LottoGroup lottoGroup = InputView.lottoGroup();
    OutputView.printBoughtLotto(lottoGroup);

    WinningLotto winningLotto = InputView.winningLotto();

    LottoResult lottoResult = new LottoResult();
    for (int i = 0; i < lottoGroup.lottoGroup().size(); i++) {
      LottoRank lottoRank = winningLotto.matchRank(lottoGroup.lottoGroup().get(i));
      lottoResult.add(lottoRank);
    }
    OutputView.printLottoResult(lottoResult);
  }
}
