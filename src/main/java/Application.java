import config.Container;
import controller.LottoController;
import factory.AutoLottoFactory;
import factory.ManualLottoFactory;
import service.LottoEvaluationService;
import service.LottoGenerateService;
import service.StatisticsService;
import view.InputView;
import view.OutputView;
import view.ViewFacade;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ViewFacade viewFacade = new ViewFacade(inputView, outputView);

        LottoGenerateService lottoGenerateService = new LottoGenerateService(new AutoLottoFactory());
        StatisticsService statisticsService = new StatisticsService();
        LottoEvaluationService lottoEvaluationService = new LottoEvaluationService(new ManualLottoFactory());

        Container container = new Container(viewFacade, lottoGenerateService, statisticsService,
                lottoEvaluationService);

        LottoController lottoController = new LottoController(container);

        lottoController.run();
    }
}
