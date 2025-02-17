import controller.LottoStatisticsController;
import controller.PurchaseController;
import controller.WinLottoController;
import model.PurchasedLottos;
import model.WinLotto;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PurchaseController purchaseController = new PurchaseController(inputView, outputView);
    private final WinLottoController winLottoController = new WinLottoController(inputView);
    private final LottoStatisticsController lottoStatisticsController = new LottoStatisticsController(outputView);

    public static void main(String[] args) {
        new LottoApplication().run();
    }

    private void run() {
        PurchasedLottos purchasedLottos = purchaseController.purchase();
        WinLotto winLotto = winLottoController.winLotto();
        lottoStatisticsController.lottoResult(purchasedLottos, winLotto);
    }
}
