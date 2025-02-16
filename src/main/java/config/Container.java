package config;

import service.LottoGenerateService;
import service.StatisticsService;
import view.InputView;
import view.OutputView;
import view.ViewFacade;

public class Container {

    private final ViewFacade viewFacade;
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerateService lottoGenerateService;
    private final StatisticsService statisticsService;

    public Container(ViewFacade viewFacade, InputView inputView, OutputView outputView,
        LottoGenerateService lottoGenerateService, StatisticsService statisticsService) {
        this.viewFacade = viewFacade;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerateService = lottoGenerateService;
        this.statisticsService = statisticsService;
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
}
