package controller;

import config.Container;
import converter.InputConverter;
import java.util.List;
import model.LottoResults;
import model.Lottos;
import model.Statistics;
import service.LottoEvaluationService;
import service.LottoGenerateService;
import service.StatisticsService;
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
        History firstHistory = processLottoPurchase();
        History secondHistory = processLottoDrawing(firstHistory);
        processStatistics(secondHistory);
    }

    private History processLottoPurchase() {
        int purchaseAmount = InputConverter.convertToInteger(viewFacade.getPurchaseInput());
        Lottos lottos = lottoGenerateService.generateLottos(purchaseAmount);
        viewFacade.printLottos(lottos.toDto());
        return new History(lottos, null);
    }

    private History processLottoDrawing(History history) {
        List<Integer> basicNumbers = InputConverter.convertToList(viewFacade.getWinningNumbers());
        int bonusNumber = InputConverter.convertToInteger(viewFacade.getBonusNumber());
        LottoResults lottoResults = lottoEvaluationService.evaluateLottos(history.lottos, basicNumbers,
                bonusNumber);
        return new History(history.lottos, lottoResults);
    }

    private void processStatistics(History history) {
        Statistics statistics = statisticsService.produceStatistics(history.lottoResults);
        viewFacade.printStatistics(statistics.toDto());
    }

    private record History(Lottos lottos, LottoResults lottoResults) {
    }

}