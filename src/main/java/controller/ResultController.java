package controller;

import java.util.List;
import java.util.stream.Collectors;
import model.Purchase;
import model.Result;
import model.WinLotto;
import model.WinLottoInfo;
import service.WinLottoInfoMapper;
import view.ResultView;

public class ResultController {
    public ResultView resultView = new ResultView();

    public void lottoResult(Purchase purchase, WinLotto winLotto) {
        List<WinLottoInfo> winResults = purchase.getLottos().stream()
                .map(lotto -> WinLottoInfoMapper.result(lotto, winLotto))
                .collect(Collectors.toList());
        Result result = new Result(winResults);

        resultView.printResult(result);
        resultView.printTotalResult(result, purchase);
    }
}
