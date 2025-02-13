package controller;

import model.Purchase;
import model.Result;
import model.WinLotto;
import view.ResultView;

public class ResultController {
    public ResultView resultView = new ResultView();

    public void lottoResult(Purchase purchase, WinLotto winLotto) {
        resultView.printResult(new Result(purchase, winLotto));
    }
}
