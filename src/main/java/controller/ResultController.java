package controller;

import model.LottoStatistics;
import model.PurchasedLottos;
import model.WinLotto;
import view.ResultView;

public class ResultController {
    public ResultView resultView = new ResultView();

    public void lottoResult(PurchasedLottos purchasedLottos, WinLotto winLotto) {
        LottoStatistics lottoStatistics = new LottoStatistics(purchasedLottos.getLottos(), winLotto);
        resultView.printResult(lottoStatistics);
        resultView.printTotalReturn(lottoStatistics, purchasedLottos);
    }
}
