package config;

import service.LottoGenerateService;
import service.LottoStatisticsService;
import view.InputView;
import view.OutputView;

public class Container {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerateService lottoGenerateService;
    private final LottoStatisticsService lottoStatisticsService;

    public Container(InputView inputView, OutputView outputView,
        LottoGenerateService lottoGenerateService, LottoStatisticsService lottoStatisticsService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerateService = lottoGenerateService;
        this.lottoStatisticsService = lottoStatisticsService;
    }

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public LottoStatisticsService getStatisticsService() {
        return lottoStatisticsService;
    }

    public LottoGenerateService getLottoGenerateService() {
        return lottoGenerateService;
    }
}
