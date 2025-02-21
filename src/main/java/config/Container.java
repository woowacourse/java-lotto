package config;

import service.LottoEvaluationService;
import service.LottoGenerateService;
import service.StatisticsService;
import view.ViewFacade;

public class Container {
    private final ViewFacade viewFacade;
    private final LottoGenerateService lottoGenerateService;
    private final StatisticsService statisticsService;
    private final LottoEvaluationService lottoEvaluationService;

    public Container(ViewFacade viewFacade, LottoGenerateService lottoGenerateService,
                     StatisticsService statisticsService, LottoEvaluationService lottoEvaluationService) {
        this.viewFacade = viewFacade;
        this.lottoGenerateService = lottoGenerateService;
        this.statisticsService = statisticsService;
        this.lottoEvaluationService = lottoEvaluationService;
    }

    public ViewFacade getViewFacade() {
        return viewFacade;
    }

    public StatisticsService getStatisticsService() {
        return statisticsService;
    }

    public LottoGenerateService getLottoGenerateService() {
        return lottoGenerateService;
    }

    public LottoEvaluationService getLottoEvaluationService() {
        return lottoEvaluationService;
    }
}
