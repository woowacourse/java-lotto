package controller;

import model.LottoStatistics;
import model.PurchasedLottos;
import model.WinLotto;
import view.OutputView;

public class LottoStatisticsController {
    private final OutputView outputView;

    public LottoStatisticsController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void lottoResult(PurchasedLottos purchasedLottos, WinLotto winLotto) {
        LottoStatistics lottoStatistics = new LottoStatistics(purchasedLottos.getLottos(), winLotto);
        outputView.printResult(lottoStatistics);
        outputView.printTotalReturn(lottoStatistics, purchasedLottos);
    }
}
