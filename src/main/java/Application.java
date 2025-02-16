import config.Container;
import controller.LottoController;
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

        LottoGenerateService lottoGenerateService = new LottoGenerateService();
        StatisticsService statisticsService = new StatisticsService();

        Container container = new Container(
            viewFacade, inputView, outputView, lottoGenerateService, statisticsService
        );

        LottoController lottoController = new LottoController(container);
        lottoController.run();
    }
}
