import controller.LottoStatisticsController;
import controller.PurchaseController;
import controller.WinLottoController;
import model.PurchasedLottos;
import model.WinLotto;

public class LottoApplication {
    private final PurchaseController purchaseController = new PurchaseController();
    private final WinLottoController winLottoController = new WinLottoController();
    private final LottoStatisticsController lottoStatisticsController = new LottoStatisticsController();

    public static void main(String[] args) {
        new LottoApplication().run();
    }

    private void run() {
        PurchasedLottos purchasedLottos = purchaseController.purchase();
        WinLotto winLotto = winLottoController.winLotto();
        lottoStatisticsController.lottoResult(purchasedLottos, winLotto);
    }
}
