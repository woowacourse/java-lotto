package controller;

import model.LottoStatistics;
import model.PurchasedLottos;
import model.WinLotto;
import view.LottoStatisticsView;

public class LottoStatisticsController {
    public LottoStatisticsView lottoStatisticsView = new LottoStatisticsView();

    public void lottoResult(PurchasedLottos purchasedLottos, WinLotto winLotto) {
        LottoStatistics lottoStatistics = new LottoStatistics(purchasedLottos.getLottos(), winLotto);
        lottoStatisticsView.printResult(lottoStatistics);
        lottoStatisticsView.printTotalReturn(lottoStatistics, purchasedLottos);
    }
}
