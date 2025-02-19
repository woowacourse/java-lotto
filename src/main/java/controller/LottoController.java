package controller;

import static util.InputConverter.convertToInteger;
import static util.InputConverter.convertToList;

import config.Container;
import domain.Lotto;
import domain.Lottos;
import domain.WinningLotto;
import dto.Statistics;
import java.util.List;
import service.LottoGenerateService;
import service.RandomLottoNumbersGenerator;
import service.StatisticsService;
import view.ViewFacade;

public class LottoController {

    private final ViewFacade viewFacade;
    private final LottoGenerateService lottoGenerateService;
    private final StatisticsService statisticsService;

    public LottoController(Container container) {
        this.viewFacade = container.getViewFacade();
        this.lottoGenerateService = container.getLottoGenerateService();
        this.statisticsService = container.getStatisticsService();
    }

    public void run() {
        PurchaseHistory purchaseHistory = processLottoPurchase();
        processLottoDrawing(purchaseHistory);
        processStatistics(purchaseHistory);
    }

    private PurchaseHistory processLottoPurchase() {
        int purchaseAmount = convertToInteger(viewFacade.getPurchaseInput());
        Lottos lottos = lottoGenerateService.generateLottos(purchaseAmount,
            new RandomLottoNumbersGenerator());
        viewFacade.printLottos(lottos);
        return new PurchaseHistory(lottos, purchaseAmount);
    }

    private void processLottoDrawing(PurchaseHistory purchaseHistory) {
        List<Integer> basicNumbers = convertToList(viewFacade.getWinningNumbers());
        Lotto basicLotto = new Lotto(basicNumbers);
        int bonusNumber = convertToInteger(viewFacade.getBonusNumber());

        WinningLotto winningLotto = new WinningLotto(basicLotto, bonusNumber);
        purchaseHistory.lottos.rankAll(winningLotto);
    }

    private void processStatistics(PurchaseHistory purchaseHistory) {
        Statistics statistics = statisticsService.produceStatistics(
            purchaseHistory.lottos,
            purchaseHistory.purchaseAmount
        );
        viewFacade.printStatistics(statistics);
    }

    private record PurchaseHistory(Lottos lottos, int purchaseAmount) {

    }
}
