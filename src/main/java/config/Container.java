package config;

import service.LottoGenerateService;
import service.LottoStatisticsService;
import view.InputView;
import view.OutputView;
import view.ViewFacade;

public class Container {

    private final ViewFacade viewFacade;
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerateService lottoGenerateService;
    private final LottoStatisticsService lottoStatisticsService;

    public Container(ViewFacade viewFacade, InputView inputView, OutputView outputView,
        LottoGenerateService lottoGenerateService, LottoStatisticsService lottoStatisticsService) {
        this.viewFacade = viewFacade;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerateService = lottoGenerateService;
        this.lottoStatisticsService = lottoStatisticsService;
    }

    public ViewFacade getViewFacade() {
        return viewFacade;
    }

    public LottoStatisticsService getStatisticsService() {
        return lottoStatisticsService;
    }

    public LottoGenerateService getLottoGenerateService() {
        return lottoGenerateService;
    }
}
