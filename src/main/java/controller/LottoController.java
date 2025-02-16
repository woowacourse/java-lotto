package controller;

import config.Container;
import factory.LottoFactory;
import java.util.List;
import model.Lotto;
import model.Lottos;
import model.Statistics;
import service.LottoEvaluationService;
import service.LottoGenerateService;
import service.StatisticsService;
import util.InputConverter;
import view.ViewFacade;

public class LottoController {
    private final ViewFacade viewFacade;
    private final LottoGenerateService lottoGenerateService;
    private final StatisticsService statisticsService;
    private final LottoEvaluationService lottoEvaluationService;

    public LottoController(Container container) {
        this.viewFacade = container.getViewFacade();
        this.lottoGenerateService = container.getLottoGenerateService();
        this.statisticsService = container.getStatisticsService();
        this.lottoEvaluationService = container.getLottoEvaluationService();
    }

    public void run() {
        PurchaseHistory purchaseHistory = processLottoPurchase();
        processLottoDrawing(purchaseHistory);
        processStatistics(purchaseHistory);
    }

    private PurchaseHistory processLottoPurchase() {
        int purchaseAmount = InputConverter.convertToInteger(viewFacade.getPurchaseInput());
        Lottos lottos = lottoGenerateService.generateLottos(purchaseAmount);
        viewFacade.printLottos(lottos.toDto());
        return new PurchaseHistory(lottos, purchaseAmount);
    }

    private void processLottoDrawing(PurchaseHistory purchaseHistory) {
        List<Integer> basicNumbers = InputConverter.convertToList(viewFacade.getWinningNumbers());
        Lotto basicLotto = LottoFactory.createCustomLotto(basicNumbers);
        int bonusNumber = InputConverter.convertToInteger(viewFacade.getBonusNumber());
        lottoEvaluationService.evaluateLottos(purchaseHistory.lottos, basicLotto, bonusNumber);
    }

    private void processStatistics(PurchaseHistory purchaseHistory) {
        Statistics statistics = statisticsService.produceStatistics(purchaseHistory.lottos,
                purchaseHistory.purchaseAmount);
        viewFacade.printStatistics(statistics.toDto());
    }

    private record PurchaseHistory(Lottos lottos, int purchaseAmount) {
    }

}