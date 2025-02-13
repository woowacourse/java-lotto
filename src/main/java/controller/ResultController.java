package controller;

import model.Purchase;
import model.Result;
import model.WinLotto;
import view.ResultView;

public class ResultController {
    public ResultView resultView = new ResultView();

    public void lottoResult(Purchase purchase, WinLotto winLotto) {
        Result result = new Result(purchase.getLottos(), winLotto);
        resultView.printResult(result);
        resultView.printTotalReturn(result, purchase);
    }
}
