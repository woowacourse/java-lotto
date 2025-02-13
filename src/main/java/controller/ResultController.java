package controller;

import model.PurchasedLottos;
import model.Result;
import model.WinLotto;
import view.ResultView;

public class ResultController {
    public ResultView resultView = new ResultView();

    public void lottoResult(PurchasedLottos purchasedLottos, WinLotto winLotto) {
        resultView.printResult(new Result(purchasedLottos, winLotto));
    }
}
