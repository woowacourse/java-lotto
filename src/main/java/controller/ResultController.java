package controller;

import model.PurchasedLottos;
import model.Result;
import model.WinLotto;
import view.ResultView;

public class ResultController {
    public ResultView resultView = new ResultView();

    public void lottoResult(PurchasedLottos purchasedLottos, WinLotto winLotto) {
        Result result = new Result(purchasedLottos.getLottos(), winLotto);
        resultView.printResult(result);
        resultView.printTotalReturn(result, purchasedLottos);
    }
}
