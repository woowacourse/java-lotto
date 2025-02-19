import config.Container;
import controller.LottoController;
import service.LottoGenerateService;
import service.LottoStatisticsService;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoGenerateService lottoGenerateService = new LottoGenerateService();
        LottoStatisticsService lottoStatisticsService = new LottoStatisticsService();

        Container container = new Container(
            inputView, outputView, lottoGenerateService, lottoStatisticsService
        );

        LottoController lottoController = new LottoController(container);
        lottoController.run();
    }
}
