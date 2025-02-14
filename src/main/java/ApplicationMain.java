import controller.LottoController;

public class ApplicationMain {
    public static void main(String[] args) {
        LottoController lottoController = LottoController.getInstance();
        lottoController.start();
    }
}