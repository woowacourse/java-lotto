import controller.PurchaseController;

public class LottoApplication {
    PurchaseController purchaseController = new PurchaseController();

    public static void main(String[] args) {
        LottoApplication lottoApplication = new LottoApplication();
        lottoApplication.run();
    }

    private void run() {
        purchaseController.printPurchaseGuide();
    }
}
